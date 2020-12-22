import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Row, Container, Col} from 'react-bootstrap';
import Figure from "react-bootstrap/Figure";
import '../assets/css/myStyle.css';
import { StyledDropZone } from 'react-drop-zone'

import { Menu, Item, Separator, Submenu, MenuProvider } from 'react-contexify';
import 'react-contexify/dist/ReactContexify.min.css';


export function FilesManagerTab(props) {

    const [login, register, exit, getLocalToken, isOnline, listFiles, uploadFile, downloadFile, deleteFile, shareFile, checkToken] = props.useApiHook();

    const onClick = ({ event, props }) => console.log(event,props);
    const {files, setFilesRefresh, cloudPath} = props;
// create your menu first
const MyAwesomeMenu = () => (
    <Menu id='menu_id'>
        <Item onClick={({ event, props })=>{downloadFile(props).then(()=>setFilesRefresh());}}>Скачать</Item>
       <Item onClick={({ event, props })=>{shareFile(props);}}>Поделиться</Item>
       <Item onClick={({ event, props })=>{deleteFile(props).then(()=>setFilesRefresh()) }}>Удалить</Item>
       <Separator />
       <Item disabled>Переименовть</Item>
       {/*<Separator />
        <Submenu label="Свойства">
        <Item onClick={onClick}></Item>
        <Item onClick={onClick}>Bar</Item>
       </Submenu> */}

    </Menu>
);


    return (

        <div>
        <MyAwesomeMenu />







            <Container fluid className='mt-3' className={"d-flex"} style={{height: "100vh", overflow: "overlay"}}>

                <div className={"flex-fill"} style={{ height: "100%" }}>
                    <Row >


                    <Figure style={{
            paddingBottom: "0px",
            paddingTop: "0.5em",
            marginBottom: "15px",
            marginLeft: "0.5em",
            paddingLeft: "0.5em",
            paddingRight: "0.5em",
borderRadius: "5px",

}}>

<StyledDropZone
  onDrop={(file, text) => uploadFile(file, cloudPath).then(()=>setFilesRefresh())}
  style={{
     width: "3em",
     height: "3em",
     zIndex: "12",
    // //background: "#ffffff85",
     //border: "1px",
     padding: "unset"
    }}
>
<div class="row" style={{height: "100%"}}>
    <div id="col" class="col-md-12 align-self-center">
  
    <svg width="20" height="20" viewBox="0 0 16 16" class="bi bi-box-arrow-down-right" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M8.636 12.5a.5.5 0 0 1-.5.5H1.5A1.5 1.5 0 0 1 0 11.5v-10A1.5 1.5 0 0 1 1.5 0h10A1.5 1.5 0 0 1 13 1.5v6.636a.5.5 0 0 1-1 0V1.5a.5.5 0 0 0-.5-.5h-10a.5.5 0 0 0-.5.5v10a.5.5 0 0 0 .5.5h6.636a.5.5 0 0 1 .5.5z"/>
  <path fill-rule="evenodd" d="M16 15.5a.5.5 0 0 1-.5.5h-5a.5.5 0 0 1 0-1h3.793L6.146 6.854a.5.5 0 1 1 .708-.708L15 14.293V10.5a.5.5 0 0 1 1 0v5z"/>
</svg>

    </div>
    </div>
    </StyledDropZone>
</Figure>


                    




                        {
                            files
                        }

                        

                    </Row>
                </div>



            </Container>
        </div>
    );
}

