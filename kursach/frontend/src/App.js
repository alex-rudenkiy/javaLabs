import React, {useEffect} from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {WelcomeFrame} from "./WelcomeFrame";
import {HomeFrame} from "./HomeFrame";
import { useSnackbar } from 'notistack';

import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";


import {useHistory} from "react-router-dom";
import {SharedFileFrame} from "./SharedFileFrame";


function useApiHook() {
    //const [isOnline] = useState(null);
    let history = useHistory();
    const axios = require('axios');
    const apiUrl = "http://localhost:8181/api/v1";
    const localUrl = "http://localhost:3000";
    const { enqueueSnackbar } = useSnackbar();

    const handleClickVariant = (text, variant) => {
        // variant could be success, error, warning, info, or default
        enqueueSnackbar(text, { variant });
    };


    const login = (login, password, remember) => {
        axios({
            method: 'post',
            url: apiUrl + '/client/login',
            data: {
                login: login,
                password: password,
                remember: remember
            },

        })
            .then(function (response) {

                if (response.data.status === "ok") {
                    console.log(response);
                    localStorage.setItem('token', response.data.payload.token);
                    localStorage.setItem('login', response.data.payload.client.name);
                    history.push("/mycloud");
                    handleClickVariant(`Добро пожаловать ${response.data.payload.client.name} !`,'success');

                }else{
                    handleClickVariant('Неправильный логин или пароль!','warning');
                }




            })
            .catch(function (error) {
                console.log(error);
                handleClickVariant(error,'error');
            });


    }

    const register = (nick, email, password, promo) => {
        if(nick!==undefined && email!==undefined && password!==undefined &&
            nick.length!==0 && email.length!==0 && password.length!==0) {
            axios({
                method: 'post',
                url: apiUrl + '/client/register',
                data: {
                    login: nick,
                    password: password,
                    email: email,
                    promo: promo
                }
            })
                .then(function (response) {
                    console.log(response);
                    if (response.data.status === "ok") {
                        login(nick, password, false);
                    } else {
                        handleClickVariant('Логин или пароль уже был ранее зарегистрирован!', 'warning');
                    }
                })
                .catch(function (error) {
                    console.log(error);
                    handleClickVariant(error, 'error');

                });
        }else {
            handleClickVariant('Необходимо заполнить все поля!', 'warning');
        }
    }

    const logout = () => {
        localStorage.clear();
        checkToken();
    }

    const getLocalToken = () => {
        return localStorage.getItem('token');
    }

    const getLocalData = () => {
        return localStorage.getItem('login');
    }

    const isOnline = () => {

    }

    const listFiles = async (path) => {
        return await new Promise((resolve, reject) => {
            axios({
                method: 'post',
                url: apiUrl + path,
                data: {
                    token: getLocalToken()
                }
            })
                .then(function (response) {
                    console.log(response.data);
                    if (response.data.status === "ok") {
                        resolve(response.data.payload);
                    } else {
                        reject(response.data);
                    }
                })
                .catch(function (error) {
                    console.log(error);
                    handleClickVariant(error,'error');
                });
        })
    }

    const uploadFile = (file, path) => {
        console.log("Закачиваю", path.slice(-1));
        var formData = new FormData();
        formData.append("file", file);
        formData.append("token", getLocalToken());

        return axios.post(apiUrl +"/upload" +path+(path.slice(-1)==="/"?"":"/"), formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })

    }

    const downloadFile = (file) => {
        const FileDownload = require('js-file-download');

        return axios({
            url: apiUrl +"/download/mycloud/"+ file.filepath,
            method: 'POST',
            data: {
                token: getLocalToken()
            },
            responseType: 'blob', // Important
        }).then((response) => {
            FileDownload(response.data, file.filename);
        });

    }

    const deleteFile = (file) => {
        return axios({
            url: apiUrl +"/delete/mycloud/"+ file.filepath,
            method: 'POST',
            data: {
                token: getLocalToken()
            },
            responseType: 'blob', // Important
        })

    }

    const createFolder = (path) => {
        //alert("создаю папку "+rootUrl +"/mkdir"+ path+"/");

        return axios({
            url: apiUrl +"/mkdir"+ path+(path.slice(-1)==="/"?"":"/"),
            method: 'POST',
            data: {
                token: getLocalToken()
            },
        })

    }


    const shareFile = async (file) => {
        return await new Promise((resolve, reject) => {
            axios({
                method: 'POST',
                url: apiUrl + "/share/mycloud/" + file.filepath,
                data: {
                    token: getLocalToken()
                }
            })
                .then(function (response) {
                    console.log(response.data);
                    if (response.data.status === "ok") {
                        handleClickVariant("Публичная ссылка: "+localUrl + '/shared/'+response.data.payload.toString(), 'success');
                        resolve(response.data.payload);
                    } else {
                        reject(response.data);
                    }
                })
                .catch(function (error) {
                    console.log(error);
                    handleClickVariant(error, 'error');
                });
        })
    }

    const getFileInfo = async (sharedfiletoken) => {
        return await new Promise((resolve, reject) => {
            axios({
                method: 'get',
                url: apiUrl + "/shared/"+sharedfiletoken,
                data: {
                    token: getLocalToken()
                }
            })
                .then(function (response) {
                    console.log(response.data);
                    if (response.data.status === "ok") {

                        resolve(response.data.payload);
                    } else {
                        reject(response.data);
                    }
                })
                .catch(function (error) {
                    console.log(error);
                    handleClickVariant(error,'error');
                });
        })

    }


    const checkToken = () => {
        axios({
            method: 'post',
            url: 'http://localhost:8181/api/v1/session/checkToken',
            params: {
                token: getLocalToken()!=null?getLocalToken():"",
            },

        })
            .then(function (response) {
                console.log(response);
                if (response.data.payload === false) {
                    history.push("/welcome");
                }
            })
            .catch(function (error) {
                console.log(error);
                handleClickVariant(error,'error');
            });

    }

    useEffect(() => {

    });

    return [login, register, logout,
        getLocalToken, isOnline, listFiles,
        uploadFile, downloadFile, deleteFile,
        shareFile, createFolder, checkToken,
        getLocalData, getFileInfo];
}

function App() {

    return (
        <div>

            <Router>
                <Switch>
                    <Route path="/welcome">
                        <WelcomeFrame useApiHook={useApiHook}/>
                    </Route>
                    <Route strict={false} exact={false} path="/mycloud">
                        <HomeFrame useApiHook={useApiHook}/>
                    </Route>
                    <Route path="/mygallery">
                        <HomeFrame useApiHook={useApiHook}/>
                    </Route>
                    <Route path="/mymusic">
                        <HomeFrame useApiHook={useApiHook}/>
                    </Route>
                    <Route path="/shared/:sharedFileId">
                        <SharedFileFrame useApiHook={useApiHook}/>
                    </Route>
                    <Route path="/">
                        <WelcomeFrame useApiHook={useApiHook}/>
                    </Route>
                </Switch>
            </Router>

        </div>
    );
}

export default App;
