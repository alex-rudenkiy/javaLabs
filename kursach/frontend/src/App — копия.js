import React from 'react';
import logo from './logo.svg';
import './App.css';
import VideoBg from "reactjs-videobg";
//import ogg from "./videos/Neon.ogg";
import webm from "./background/video.webm";
import mp4 from "./background/video.mp4";
// import poster from "./img/poster.jpg";
import 'bootstrap/dist/css/bootstrap.min.css';


function App() {
  return (
    <div className="App">
      <header className="App-header">

        </header>

        <body>
        <VideoBg>
        <VideoBg.Source src={webm} type="video/webm" />
        <VideoBg.Source src={mp4} type="video/mp4" />
        </VideoBg>
          {/* <div className="">
          <div  style={{    background: "white",  height: "29em"}}>
            </div>

          </div> */}
            
        </body>

    </div>
  );
}

export default App;
