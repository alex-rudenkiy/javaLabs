import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import clsx from 'clsx';
import Card from '@material-ui/core/Card';
import CardHeader from '@material-ui/core/CardHeader';
import CardMedia from '@material-ui/core/CardMedia';
import CardContent from '@material-ui/core/CardContent';
import CardActions from '@material-ui/core/CardActions';
import Collapse from '@material-ui/core/Collapse';
import Avatar from '@material-ui/core/Avatar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import { red } from '@material-ui/core/colors';
import FavoriteIcon from '@material-ui/icons/Favorite';
import ShareIcon from '@material-ui/icons/Share';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import MoreVertIcon from '@material-ui/icons/MoreVert';
import PlayArrowIcon from '@material-ui/icons/PlayArrow';
//import Art from 'public/images/Summertime -  Kreayshawn feat. V-Nasty.jpg'
import Menu from '@material-ui/core/Menu';
import MenuItem from '@material-ui/core/MenuItem';

const useStyles = makeStyles((theme) => ({
    root: {
        maxWidth: 345,
    },
    media: {
        height: 0,
        paddingTop: '56.25%', // 16:9
    },
    expand: {
        transform: 'rotate(0deg)',
        marginLeft: 'auto',
        transition: theme.transitions.create('transform', {
            duration: theme.transitions.duration.shortest,
        }),
    },
    expandOpen: {
        transform: 'rotate(180deg)',
    },
    avatar: {
        backgroundColor: red[500],
    },
}));

export default function AudioCard(props) {
    const classes = useStyles();
    const [expanded, setExpanded] = React.useState(false);

    const handleExpandClick = () => {
        setExpanded(!expanded);
    };

    const [anchorEl, setAnchorEl] = React.useState(null);

    const handleClick = (event) => {
      setAnchorEl(event.currentTarget);
    };
  
    const handleClose = () => {
      setAnchorEl(null);
    };

    return (
        <div >

        <Card className={classes.root} >






           <CardMedia
                className={classes.media}
                //image={Art}
                title="Paella dish"
                image={process.env.PUBLIC_URL + '/images/Summertime -  Kreayshawn feat. V-Nasty.jpg'}
            />
                <CardHeader
                    avatar={
                        <IconButton aria-label="play/pause" onClick={()=>props.onClickPlay("Yellow_Claw_-_City_on_Lockdown_47828766.mp3")}>
                            <PlayArrowIcon />
                        </IconButton>
                    }

                    title="Shrimp and Chorizo Paella"
                    subheader="September 14, 2016"
                />

            <CardActions disableSpacing>
                <IconButton aria-label="add to favorites">
                    <FavoriteIcon />
                </IconButton>
                <IconButton aria-label="share">
                    <ShareIcon />
                </IconButton>



                <IconButton aria-label="settings" aria-controls="simple-menu" aria-haspopup="true" style={{marginLeft: 'auto'}} onClick={handleClick}>
                    <MoreVertIcon />
                </IconButton>


            </CardActions>

            <Menu
  id="simple-menu"
  anchorEl={anchorEl}
  keepMounted
  open={Boolean(anchorEl)}
  onClose={handleClose}
>
  <MenuItem onClick={handleClose}>Подробнее</MenuItem>
  <MenuItem onClick={handleClose}>Удалить</MenuItem>
</Menu>

        </Card>
        </div>
    );
}
