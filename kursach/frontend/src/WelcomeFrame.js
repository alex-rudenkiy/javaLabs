import React, {useState, useCallback, useEffect, useRef} from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import {createMuiTheme} from '@material-ui/core/styles';
import {Row, Container, Col} from 'react-bootstrap';
import {TabPanel} from "./TabPanel";
import 'react-h5-audio-player/lib/styles.css';
import Button from '@material-ui/core/Button';
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import TextField from "@material-ui/core/TextField";
import CardActions from "@material-ui/core/CardActions";
import {AuthPanel} from "./components/AuthPanel";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";


const theme = createMuiTheme({
    palette: {
        primary: {
            light: '#757ce8',
            main: '#3f50b5',
            dark: '#002884',
            contrastText: '#fff',
        },
        secondary: {
            light: '#ff7961',
            main: '#f44336',
            dark: '#ba000d',
            contrastText: '#000',
        },
    },
});


export function WelcomeFrame(props) {

    const [login, register, exit, getLocalToken, isOnline, listFiles, uploadFile, downloadFile, deleteFile, shareFile] = props.useApiHook();

    const [panelIndex, setPanelIndex] = useState(0);
    const [authFields, setAuthFields] = useState({remember: false});


    return (
        <div className="App">
            <header>

            </header>


            <body>

            <AppBar style={{}} position="static">
                <Toolbar style={{
                    marginTop: "0.5em",
                    marginBottom: "0.5em"
                }}>
                    <Typography variant="h6">
                        MyCloud
                    </Typography>

                    {/*<div style={{marginLeft: "auto"}}>
                        <Typography variant="h6">
                        News
                    </Typography>
                    </div>*/}


                    {/* <IconButton edge="start" color="inherit" aria-label="menu">
      <MenuIcon />
    </IconButton>
    <Button color="inherit">Login</Button>
     */}

                    {/* authFields.remember.toString()*/}
                </Toolbar>
            </AppBar>


            <Container fluid className='mt-3' style={{paddingLeft: "2em", paddingRight: "2em"}}>
                <Row>
                    <Col xs={5} sm={5} md={5} lg={5} xl={3}>
                        <Card className="pb-4 pt-0">


                            <AuthPanel panelIndex={panelIndex} setPanelIndex={setPanelIndex}/>

                            <div>
                                <TabPanel value={panelIndex} index={0} className={"pt-0"}>
                                    <CardContent className={"p-0"}>
                                        <div style={{marginLeft: "2em", marginRight: "2em"}}>

                                            <TextField id="outlined-basic" label="Логин"
                                                       onChange={(e) => setAuthFields({...authFields, ...{"login": e.target.value}})}
                                                       style={{width: "100%", marginTop: "1em"}}/>
                                            <TextField id="outlined-basic" label="Пароль"
                                                       onChange={(e) => setAuthFields({...authFields, ...{"password": e.target.value}})}
                                                       type={"password"} style={{width: "100%", marginTop: "1em"}}/>
                                            <FormControlLabel
                                                control={
                                                    <Checkbox
                                                        checked={authFields.remember}
                                                        onChange={(e) => setAuthFields({...authFields, ...{"remember": e.target.checked}})}

                                                        name="checkedB"
                                                        color="primary"
                                                    />
                                                }
                                                label="Запомнить"
                                            />

                                        </div>
                                    </CardContent>
                                    <CardActions className="float-left ml-4 mt-3 ">
                                        <Button variant="contained" color="primary" size="medium"
                                                onClick={() => login(authFields.login, authFields.password, authFields.remember)}>Войти</Button>
                                    </CardActions>
                                </TabPanel>
                            </div>


                            <TabPanel value={panelIndex} index={1} className={"pt-0"}>
                                <CardContent className={"p-0"}>
                                    <div style={{marginLeft: "2em", marginRight: "2em"}}>

                                        <TextField id="outlined-basic" label="Логин"
                                                   onChange={(e) => setAuthFields({...authFields, ...{"login": e.target.value}})}
                                                   style={{width: "100%", marginTop: "1em"}}/>
                                        <TextField id="outlined-basic" label="Почта"
                                                   onChange={(e) => setAuthFields({...authFields, ...{"email": e.target.value}})}
                                                   style={{width: "100%", marginTop: "1em"}}/>
                                        <TextField id="outlined-basic" label="Пароль"
                                                   onChange={(e) => setAuthFields({...authFields, ...{"password": e.target.value}})}
                                                   type={"password"} style={{width: "100%", marginTop: "1em"}}/>
                                        <TextField id="outlined-basic" label="Промо-код"
                                                   onChange={(e) => setAuthFields({...authFields, ...{"promo": e.target.value}})}
                                                   style={{width: "100%", marginTop: "1em"}}/>
                                    </div>

                                </CardContent>
                                <CardActions className="float-left ml-4 mt-3 ">
                                    <Button variant="contained" color="primary" size="medium"
                                            onClick={() => register(authFields.login, authFields.email, authFields.password, authFields.promo)}>Зарегистрироваться</Button>
                                </CardActions>
                            </TabPanel>


                        </Card>
                    </Col>
                    <Col>
                        <Card elevation={0}>
                            <CardContent>
                                <Typography color="textSecondary" gutterBottom>
                                    Мы сохраним все ваши файлы!
                                </Typography>
                                <div style={{
                                    textAlign: "center",
                                    width: "57%",
                                    margin: "auto",
                                    minWidth: "300px",
                                    maxWidth: "1000px"
                                }}>
                                    <img src={`${process.env.PUBLIC_URL}/images/2020-10-18_01-00-40.svg`}
                                         className="img-fluid" alt="Responsive image"/>
                                </div>

                            </CardContent>
                        </Card>
                    </Col>
                </Row>
            </Container>


            </body>

        </div>);

}

