import React, {useEffect, useState} from "react";
import Typography from "@material-ui/core/Typography";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import CardActions from "@material-ui/core/CardActions";
import Button from "@material-ui/core/Button";
import {useParams} from "react-router";
import GetAppIcon from '@material-ui/icons/GetApp';
import * as axios from "axios";


export function SharedFileFrame(props) {
    let {sharedFileId} = useParams();

    const [login, register, logout,
        getLocalToken, isOnline, listFiles,
        uploadFile, downloadFile, deleteFile,
        shareFile, createFolder, checkToken,
        getLocalData, getFileInfo] = props.useApiHook();

    const downloadSharedFile = (link) => {
        const FileDownload = require('js-file-download');

        return axios(
            {
                url: link,
                method: 'POST',
                data: {
                    token: getLocalToken()
                },
                responseType: 'blob', // Important
            }
        ).then(
            (response) => {
                FileDownload(response.data, fileInfo.filepath);
            }
        );

    }

    const [fileInfo, setFileInfo] = useState("");
    const [authFields, setAuthFields] = useState({remember: false});
    console.log("trig");
    useEffect(async () => {
        setFileInfo(await getFileInfo(sharedFileId))
    }, []);
    //<h3>ID: {fileInfo.filepath}</h3>

    /*<IconButton aria-label="delete">
        <GetAppIcon fontSize="large" />
    </IconButton>*/
    return (
        <div className="container h-100" style={{position: "absolute", right: "0px", left: "0px"}}>
            <div className="row align-items-center h-100">
                <div className="col-6 mx-auto">
                    <Card>
                        <CardContent>
                            <Typography component="h5" variant="h5">
                                {fileInfo.filepath}
                            </Typography>
                            <Typography variant="subtitle1" color="textSecondary">
                                Владелец: {fileInfo.client !== undefined ? fileInfo.client.name : ""}
                            </Typography>
                        </CardContent>
                        <CardActions>
                            <Button size="small" onClick={() => downloadSharedFile("http://localhost:8181/api/v1/shared/"+fileInfo.id)}>Скачать<GetAppIcon
                                fontSize="small"/></Button>
                        </CardActions>
                    </Card>
                </div>
            </div>
        </div>


        /*    <div className="container h-100 d-flex justify-content-center">
                <div className="jumbotron my-auto">
                    <h1 className="display-3">Hello, world!</h1>
                </div>
            </div>*/


    );

}
