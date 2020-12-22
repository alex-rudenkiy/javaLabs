import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import {createMuiTheme} from '@material-ui/core/styles';
import {Row, Container, Col} from 'react-bootstrap';
import FolderSharedIcon from '@material-ui/icons/FolderShared';
import Image from "react-bootstrap/Image";
import Figure from "react-bootstrap/Figure";
import {ItemFigure} from "./ItemFigure";
import '../assets/css/myStyle.css';
import AudioCard from "./AudioCard";

export function AudioTab(props) {
    let files=[]
    for (let i = 0; i < 30; i++) {
        files.push(new AudioCard({onClickPlay:props.playMusicFunc}));
    }
    return (
        <div>
            <Container fluid className='mt-3' className={"d-flex"} >

                <div className={"flex-fill"} style={{ height: "100%" }}>
                    <Row >

                        {
                            files.map(e=><div className={"m-3"}>{e}</div>)
                        }


                    </Row>
                </div>



            </Container>
        </div>

    );
}

