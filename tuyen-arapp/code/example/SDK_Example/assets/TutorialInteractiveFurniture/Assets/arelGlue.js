var showGeometries = false;var chair, tv, screenMovie, metaioMan;arel.sceneReady(function(){	arel.Scene.setTrackingConfiguration("ORIENTATION_FLOOR");	//get the metaio man model reference	metaioMan = arel.Object.Model3D.create("1","Assets/metaioman.md2","Assets/metaioman.png");	metaioMan.setVisibility(false);	metaioMan.setCoordinateSystemID(1);	metaioMan.setScale(new arel.Vector3D(10.0,10.0,10.0));	arel.Scene.addObject(metaioMan);	metaioMan.startAnimation("idle", true);	metaioMan.setPickingEnabled(true);	// get chair model reference	chair = arel.Object.Model3D.create("2","Assets/stuhl.obj","Assets/stuhl.jpg");	chair.setVisibility(false);	chair.setCoordinateSystemID(1);	chair.setScale(new arel.Vector3D(100.0,100.0,100.0));	var chairRotation = new arel.Rotation();	chairRotation.setFromEulerAngleDegrees(new arel.Vector3D(90.0,0.0,0.0));	chair.setRotation(chairRotation);	arel.Scene.addObject(chair);	chair.setPickingEnabled(true);	tv = arel.Object.Model3D.create("3","Assets/tv.obj","Assets/tv.png");	tv.setVisibility(false);	tv.setCoordinateSystemID(1);	tv.setScale(new arel.Vector3D(100.0,100.0,100.0));	var tvRotation = new arel.Rotation();	tvRotation.setFromEulerAngleDegrees(new arel.Vector3D(90.0,0.0,0.0));	tv.setRotation(tvRotation);	arel.Scene.addObject(tv);	tv.setPickingEnabled(true);	// the parameters for the screen should be in agreement with those of the TV	screenMovie = arel.Object.Model3D.create("4","Assets/screen.obj");	screenMovie.setVisibility(false);	screenMovie.setCoordinateSystemID(1);	screenMovie.setScale(new arel.Vector3D(100.0,100.0,100.0));	screenMovie.setRotation(tvRotation);	screenMovie.setMovie("Assets/sintel.3g2");	arel.Scene.addObject(screenMovie);	screenMovie.setPickingEnabled(true);});function onResetButtonClicked(){	showGeometries = false;	// hide the geometries before reactivating the camera	metaioMan.setVisibility(false);	chair.setVisibility(false);	tv.setVisibility(false);	screenMovie.setVisibility(false);	screenMovie.stopMovieTexture();	// remove the geometries from the gesture handler	arel.GestureHandler.removeObject("1");	arel.GestureHandler.removeObject("2");	arel.GestureHandler.removeObject("3");	arel.GestureHandler.removeObject("4");	// change the button image to unselected	document.getElementById("manImage").src = "Assets/button_man_unselected.png";	document.getElementById("chairImage").src = "Assets/button_chair_unselected.png";	document.getElementById("tvImage").src = "Assets/button_tv_unselected.png";	arel.Scene.setFreezeTracking(false);	arel.Scene.setTrackingConfiguration("ORIENTATION_FLOOR");};function onSaveScreenshotButtonClicked(){	arel.Scene.shareScreenshot(true);	alert("Screenshot will be saved in the gallery.");};function onTakePictureButtonClicked(){	arel.Scene.setFreezeTracking(true);	// add the geometries to the gesture handler	arel.GestureHandler.addObject( "1", 1 );	arel.GestureHandler.addObject( "2", 2 );	arel.GestureHandler.addObject( "3", 3 );	arel.GestureHandler.addObject( "4", 3 );	showGeometries = true;};function onManButtonClicked(){	// image button not pushable if the camera is still active	if (showGeometries)	{		// if metaioMan not visible, set it to visible and reset the parameters		if (!metaioMan.getVisibility().liveview)		{			metaioMan.setScale(new arel.Vector3D(10.0,10.0,10.0));			arel.Scene.get3DPositionFromViewportCoordinates(new arel.Vector2D(0.5,0.5), 1, translationMan);			document.getElementById("manImage").src = "Assets/button_man_selected.png";		}		else		{			metaioMan.setVisibility(false);			document.getElementById("manImage").src = "Assets/button_man_unselected.png";		}	}	else	{		alert("Please take a picture first!");	}};function translationMan(trans){	metaioMan.setTranslation(trans);	metaioMan.setVisibility(true);};function onChairButtonClicked(){	if (showGeometries)	{		if (!chair.getVisibility().liveview)		{			chair.setScale(new arel.Vector3D(100.0,100.0,100.0));			arel.Scene.get3DPositionFromViewportCoordinates(new arel.Vector2D(0.5,0.5), 1, translationChair);			document.getElementById("chairImage").src = "Assets/button_chair_selected.png";		}		else		{			chair.setVisibility(false);			document.getElementById("chairImage").src = "Assets/button_chair_unselected.png";		}	}	else	{		alert("Please take a picture first!");	}};function translationChair(trans){	chair.setTranslation(trans);	chair.setVisibility(true);};function onTVButtonClicked(){	if (showGeometries)	{		if (!tv.getVisibility().liveview)		{			tv.setScale(new arel.Vector3D(100.0,100.0,100.0));			screenMovie.setScale(new arel.Vector3D(100.0,100.0,100.0));			arel.Scene.get3DPositionFromViewportCoordinates(new arel.Vector2D(0.5,0.5),1, translationTV);			document.getElementById("tvImage").src = "Assets/button_tv_selected.png";		}		else		{			tv.setVisibility(false);			screenMovie.setVisibility(false);			screenMovie.stopMovieTexture();			document.getElementById("tvImage").src = "Assets/button_tv_unselected.png";		}	}	else	{		alert("Please take a picture first!");	}};function translationTV(trans){	tv.setTranslation(trans);	screenMovie.setTranslation(trans);	tv.setVisibility(true);	screenMovie.setVisibility(true);	screenMovie.startMovieTexture();};