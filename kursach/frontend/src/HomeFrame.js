import React, {useState, useCallback, useEffect, useRef} from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Typography from '@material-ui/core/Typography';
import {createMuiTheme} from '@material-ui/core/styles';
import {Row, Container, Col} from 'react-bootstrap';
import FullWidthTabs from "./TabPanel";
import {FilesManagerTab} from "./components/FilesManagerTab";
import 'react-h5-audio-player/lib/styles.css';
import AudioPlayer from 'react-h5-audio-player';
import {SearchTextBox} from "./components/SearchTextBox";
import SortIcon from '@material-ui/icons/Sort';
import FolderIcon from '@material-ui/icons/Folder';
import ReactDOMServer from 'react-dom/server';
import {motion} from "framer-motion";
import Button from '@material-ui/core/Button';
import {HeaderBar} from "./components/HeaderBar";
import {BrowserRouter as Router, Route, Switch, useHistory, useLocation} from 'react-router-dom'
import {MenuProvider} from "react-contexify";
import {ItemFigure} from "./components/ItemFigure";
import FormDialog from "./components/ModalDialog";
import {useSvgSource} from "./components/UseSvgSource";
import CreateNewFolderIcon from '@material-ui/icons/CreateNewFolder';


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

function showTags(url) {


    //const tags = NodeID3.read(url)
    //NodeID3.read(url, function(err, tags) {})


}


export function HomeFrame(props) {
    const [login, register, exit, getLocalToken, isOnline, listFiles, uploadFile, downloadFile, deleteFile, shareFile, createFolder, checkToken] = props.useApiHook();

    const location = useLocation();
    const history = useHistory();
    const [bodyBlurred, setBodyBlurred] = useState(false);
    const [track, setTrack] = useState("");
    const [showPlayer, setShowPlayer] = useState(false);
    const [cloudPath, setCloudPath] = useState(location.pathname);
    const [currentPageIndex, setCurrentPageIndex] = React.useState(location.pathname);//props.children[0].props.title
    const [files, setFiles] = useState([]);
    const [filesRefresh, setFilesRefresh] = useState(false);

    const [openModalDialog, setOpenModalDialog] = React.useState(false);
    const [newDirName, setNewDirName] = useState("");



    useEffect(() => {

        checkToken();



        setFiles([]);
        //console.log("gen");
        //alert(location.pathname);







        setCloudPath(location.pathname);
        setCurrentPageIndex("/"+location.pathname.split("/")[1]);


        const r = listFiles(location.pathname).then((f)=>{

            let result = [];


            Object.entries(f).forEach(([key, value]) =>
                result.push( <MenuProvider id="menu_id" style={{  display: 'inline-block' }} data={value}><ItemFigure data={value}/></MenuProvider>)
            );

            setFiles(result);
            console.log("files = ",files);
        })

        setFilesRefresh(false);
    }, [filesRefresh, location.pathname]);


    useEffect(() => {
        //setCurrentPageIndex(currentPageIndex);
        history.push(currentPageIndex);
    }, [currentPageIndex]);


    //const [bgImage, setBgImage] = useState(Art);

    const playerRef = useRef();

    useEffect(() => {
        console.log(
            ReactDOMServer.renderToString(
                <Row>
                    <Typography className={"mb-4"} variant="h6">
                        Все файлы
                    </Typography>
                    <div><SearchTextBox/></div>
                    <div style={{
                        margin: "auto",
                        marginRight: "1em"
                    }}><SortIcon/></div>
                </Row>
            )
        );
    }, []);

    const playFunc = (trackName) => {
        //const axios = require('axios');
        setTrack(`${process.env.PUBLIC_URL}/music/${trackName}`);
        playerRef.current.audio.current.play();
        showTags(`${process.env.PUBLIC_URL}/music/${trackName}`);
    }


    return (

        <div>
            {/* <div style={{backgroundImage: `url("${process.env.PUBLIC_URL}/images/Rip N Dip - Getter.jpg")`,     filter: "blur(40px)",
    width: "120%",
    height: "80%",
    backgroundSize: "cover",
    position: "absolute",
    zIndex: "0",
    marginLeft: "-10%"}}></div> */}


            {/*<SettingsTab/>*/}


            <header>
                <useSvgSource/>
            </header>

            <div
                style={{backgroundImage: "url(https://images.unsplash.com/photo-1508144322886-717c284ab392?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=800&h=533&fit=crop&ixid=eyJhcHBfaWQiOjF9)"}}>

            </div>


            <body style={{height: "100vh"}}>


            <HeaderBar apiHook = {props.useApiHook} />


            <Container className="h-100 d-inline-block" fluid className='mt-3'
                       style={{paddingLeft: "2em", paddingRight: "2em", marginBottom: "5em"}}>


                <FullWidthTabs  style={{maxHeight: "inherit"}} currentPageIndex={currentPageIndex} setCurrentPageIndex={setCurrentPageIndex}>

                    <div index={"/mycloud"} title="Мои файлы" icon={<FolderIcon/>} subroute={()=>{setCurrentPageIndex("/mycloud")}}>

                        <Row>
                            <Typography className={"mb-4"} variant="h6">
                                Все файлы
                            </Typography>
                            <div><SearchTextBox/></div>

                            <div style={{
                                margin: "auto",
                                marginRight: "1em"
                            }}>
                                <Button variant="contained" style={{backgroundColor:"#fdc300", color:"white"}} disableElevation onClick={()=>setOpenModalDialog(true)}>
                                Создать папку <CreateNewFolderIcon style={{marginLeft:"0.2em"}}/>
                            </Button>

                                <FormDialog open={openModalDialog} setOpen={(v)=>setOpenModalDialog(v)} textField={newDirName} setTextField={(v)=>setNewDirName(v)} createFolder={(p)=>{createFolder(`${location.pathname}${((p[0]==="/"||location.pathname[location.pathname.length-1]==="/")?"":"/")}${p}`).then(()=>setFilesRefresh(true))}}/>
                                {/*<SortIcon/>*/}
                            </div>
                        </Row>

                        <FilesManagerTab files={files} useApiHook={props.useApiHook} setFilesRefresh={()=>setFilesRefresh(true)} cloudPath={cloudPath}/>
                    </div>

 {/*                   <div index={"/mygallery"} title="Галерея" icon={<ImageIcon/>} subroute={()=>{setCurrentPageIndex("/mygallery")}}>
                        <Typography className={"mb-4"} variant="h6">
                            Галерея
                        </Typography>
                        <GalleryTab/>
                    </div>

                    <div index={"/mymusic"} title="Музыка" icon={<MusicNoteIcon/>} subroute={()=>{setCurrentPageIndex("/mymusic")}}>
                        <Typography className={"mb-4"} variant="h6">
                            Музыка
                        </Typography>
                        <AudioTab playMusicFunc={playFunc}/>
                    </div>*/}

                </FullWidthTabs>

            </Container>


            </body>

{/*
            <footer className={"fixed-bottom"}>

                <motion.div
                    //className="container"
                    initial={{scale: 0}}
                    animate={{scale: 1}}
                    transition={{duration: 0.5}}

                >
                    <AudioPlayer style={{background: "#e0e0e0d1", backdropFilter: "blur(2px)", width: "100%"}}
                                 src={track} ref={playerRef}/>
                </motion.div>
            </footer>
*/}

        </div>
    );
}

