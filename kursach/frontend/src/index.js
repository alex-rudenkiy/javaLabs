import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import {createBrowserHistory} from 'history'
import {SnackbarProvider} from "notistack";

const history = createBrowserHistory();


ReactDOM.render(
  <React.StrictMode>
      <SnackbarProvider maxSnack={3} anchorOrigin={{
          vertical: 'bottom',
          horizontal: 'right',
      }}>
          <App />
      </SnackbarProvider>
  </React.StrictMode>,
  document.getElementById('root')
);

serviceWorker.unregister();
