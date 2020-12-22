import React, {useState} from 'react';
//import Art from 'public/images/Summertime -  Kreayshawn feat. V-Nasty.jpg'
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import TextField from "@material-ui/core/TextField";
import DialogActions from "@material-ui/core/DialogActions";

export default function FormDialog(props) {
    const {open, setOpen, textField, setTextField, createFolder} = props;

    return (

            <Dialog open={open} onClose={()=>setOpen(false)} aria-labelledby="form-dialog-title">
                <DialogTitle id="form-dialog-title">Создание папки</DialogTitle>
                <DialogContent>
                    {/*<DialogContentText>
                        Введите название папки
                    </DialogContentText>*/}
                    <TextField
                        autoFocus
                        margin="dense"
                        id="name"
                        label="Название папки"
                        value={textField}
                        type="text"
                        fullWidth
                        onChange={(v)=>setTextField(v.target.value)}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={()=>setOpen(false)} color="primary">
                        Закрыть
                    </Button>
                    <Button onClick={()=>{createFolder(textField); setOpen(false);}} color="primary">
                        Создать
                    </Button>
                </DialogActions>
            </Dialog>

    );
}
