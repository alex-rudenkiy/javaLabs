import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import {Row, Container, Col} from 'react-bootstrap';
import Image from "react-bootstrap/Image";
import '../assets/css/myStyle.css';
import 'react-contexify/dist/ReactContexify.min.css';
import Button from "@material-ui/core/Button/Button";
import MeetingRoomIcon from '@material-ui/icons/MeetingRoom';
import LogoIcon from "../assets/images/database.svg";



export function HeaderBar(props) {
    const [login, register, logout, getLocalToken, isOnline, listFiles, uploadFile, downloadFile, deleteFile, shareFile, createFolder, checkToken, getLocalData] = props.apiHook();



    return (

        <AppBar style={{
            backgroundColor: "transparent",
            boxShadow: "none", color: "dimgrey", paddingLeft: "3.5em", paddingTop: "1em"
        }} position="static">
            <Toolbar style={{
                marginTop: "0.5em",
                marginBottom: "0.5em",
            }}>


                <Container style={{ margin: "inherit", display: "contents", width:"100%" }}>

{/*
                    <FilterDramaTwoToneIcon style={{ height: "auto", width: "2em" }} color="primary" />
*/}
                    <div><Image src={LogoIcon} style={{width: "2em"}} /></div>
                    <Col>
{/*
                        <SvgIcon component={LogoIcon} viewBox="0 0 600 476.6" />
*/}


                        <Typography color="textPrimary" variant="h6" style={{fontWeight: "600"}}>
                            MyCloud
                        </Typography>
                        {/*style={{marginLeft: "auto"}}*/}

                        <Typography variant="subtitle2" color="textSecondary">
                            {getLocalData()}
                        </Typography>
                    </Col>

                    <Button disableElevation style={{    padding: "1em",
                        paddingLeft: "2em",
                        paddingRight: "2em"
                    }} onClick={()=>logout()}><Typography variant="subtitle2"style={{  }}  >
                        Выход <MeetingRoomIcon/>
                    </Typography></Button>




                </Container>


            </Toolbar>
        </AppBar>
    );
}

