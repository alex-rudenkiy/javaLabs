import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Typography from '@material-ui/core/Typography';
import '../assets/css/myStyle.css';
import 'react-contexify/dist/ReactContexify.min.css';
import { makeStyles, withStyles } from '@material-ui/core/styles';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';


const StyledTabs = withStyles({
    indicator: {
        display: 'flex',
        justifyContent: 'center',
        backgroundColor: 'transparent',
        '& > span': {
            maxWidth: 40,
            width: '100%',
            backgroundColor: '#635ee7',
        },
    },
})((props) => <Tabs {...props} TabIndicatorProps={{ children: <span /> }} />);

const StyledTab = withStyles((theme) => ({
    root: {
        textTransform: 'none',
        color: '#979797',
        fontWeight: theme.typography.fontWeightRegular,
        fontSize: theme.typography.pxToRem(15),
        marginRight: theme.spacing(1),
        '&:focus': {
            opacity: 1,
        },
    },
}))((props) => <Tab disableRipple {...props} />);

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    padding: {
        padding: theme.spacing(0),
    },
    demo1: {
        backgroundColor: theme.palette.background.paper,
    },
    demo2: {
        backgroundColor: '#ffffff',
    },
}));


export function AuthPanel(props) {
    const {panelIndex,setPanelIndex} = props;

    const classes = useStyles();
    //const [value, setValue] = React.useState(0);

    const handleChange = (event, newValue) => {
        setPanelIndex(newValue);
    };


    return (

        <div className={classes.demo2} >

            <StyledTabs value={panelIndex} onChange={handleChange} aria-label="styled tabs example" >
                <StyledTab label="Авторизация" />
                <StyledTab label="Регистрация" />
            </StyledTabs>
            <Typography className={classes.padding} />


        </div>

    );
}

