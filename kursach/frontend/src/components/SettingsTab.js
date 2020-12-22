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
import {useCallback} from 'react'
import {useDropzone} from 'react-dropzone'
import {StyledDropZone} from 'react-drop-zone'

import {Menu, Item, Separator, Submenu, MenuProvider} from 'react-contexify';
import 'react-contexify/dist/ReactContexify.min.css';
import {Transition} from 'react-transition-group';
import {motion} from "framer-motion";
import Button from "@material-ui/core/Button/Button";
import FilterDramaTwoToneIcon from '@material-ui/icons/FilterDramaTwoTone';
import SettingsIcon from '@material-ui/icons/Settings';
import CloseIcon from '@material-ui/icons/Close';
import IconButton from '@material-ui/core/IconButton';
import TextField from "@material-ui/core/TextField/TextField";

export function SettingsTab() {
    const variants = {
        visible: {scale: 1},
        hidden: {scale: 0},
    }


    return (
        <motion.div
            initial={variants.visible}
            animate={variants.hidden}
            variants={variants}
            transition={{
                type: "spring",
                stiffness: 350,
                damping: 255
            }}
            style={{position: "fixed", zIndex: "2", display: "flex"}}
        >
            <Container fluid className='mt-3' className={"d-flex"}
                       style={{height: "100vh", width: "100vw", overflow: "overlay", background: "#ecf0f1"}}>


                <AppBar style={{
                    backgroundColor: "transparent",
                    boxShadow: "none", color: "dimgrey", paddingLeft: "3.5em", paddingTop: "1em"
                }} position="static">
                    <Toolbar style={{
                        marginTop: "0.5em",
                        marginBottom: "0.5em",
                    }}>


                        <Container style={{margin: "inherit", display: "contents", width: "100%"}}>

                            <Col>
                                <Typography variant="h5" style={{}}>
                                    <SettingsIcon/>
                                    Настройки
                                </Typography>
                            </Col>

                            <IconButton color="primary" aria-label="upload picture" component="span">
                                <CloseIcon/>
                            </IconButton>



                        </Container>



                    </Toolbar>
                </AppBar>


                <div>
                    <TextField id="filled-basic" label="Filled" variant="filled" />

                </div>
            </Container>


        </motion.div>

    );
}

