//create onload function
onLoadFunc = {
    arg thisLayout;
    
    //show window - this is the id attribute of the window tag in scene.sc, ie. id="keyboard_operator_window"
    thisLayout.getLayoutItem("keyboard_operator_window").front;
}

//load scene
Layouts.load(”scene.sc”, onLoadFunction);