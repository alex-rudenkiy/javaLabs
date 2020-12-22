import React, {useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Figure from "react-bootstrap/Figure";
import {useHistory} from "react-router-dom";
import Axios from "axios";



export function ItemFigure(props) {

    const [img, setImg] = useState("");
    const [isClicked, setIsClicked] = useState(false);
    const [lastTimeClick, setLastTimeClick] = useState(Math.round(new Date() / 1000));
    const history = useHistory();
    const path = require('path');

    const onDoubleClicked = ()=>{
        setIsClicked(!isClicked);
        console.log(Math.abs(new Date()-lastTimeClick));
        if((new Date()-lastTimeClick)<300){
            //alert("clicked");
            history.push("/mycloud/"+props.data.filename);
        }

        setLastTimeClick(new Date());
    }

    useEffect(()=> {
        console.log("test");
            Axios({
                url: props.data.isDir?"/images/folder.svg":"/images/file.svg",
                method: 'GET',
            }).then((response) => {
                //console.log("gen", response.data);
                setImg("data:image/svg+xml;base64," + btoa(response.data.replace("PNG",props.data.filename.split(".").pop().toUpperCase())));
            });

        }
    ,[img]);

    return (
        <Figure onClick={()=>onDoubleClicked()}

                style={{width:"5em",
            paddingBottom: "5px",
            paddingTop: "0.5em",
            marginBottom: "15px",
            //marginLeft: "0.5em",
            paddingLeft: "0.5em",
            paddingRight: "0.5em",
            textAlign: "center",
            boxShadow: (isClicked)?"0 0 10px rgba(0,0,0,0.35)":"unset",
            borderRadius: "5px"

        }}>
            
            <Figure.Image
                width={50}
                height={50}
                src={img}
            />
            <Figure.Caption >
                <p style={{
                    textAlign: "center",
/*
                    textDecoration: "none",
                    textOverflow: "ellipsis",
                    whiteSpace: "nowrap",
*/
                    overflow: "hidden",
                    margin: "unset",
                    wordWrap: "break-word"
                }}>{path.basename(props.data.filename).indexOf(".")>0? decodeURI(path.basename(props.data.filename).substring(0,props.data.filename.lastIndexOf("."))):decodeURI(path.basename(props.data.filename))}</p>
            </Figure.Caption>
        </Figure>
    );
}

