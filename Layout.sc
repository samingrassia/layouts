/*
	License: http://www.gnu.org/licenses/gpl-3.0.txt
	Author: Sam Ingrassia - sam@samingrassia.com
*/
	Layout {
		var <>layoutName;
		var <>layoutItems;
		
		//----------------------------------------------------------------------------------------------//
		load { 
			arg node;
			
			//create new layout item dictionary
			this.layoutItems = Dictionary.new;
			
			//set layoutname
			this.layoutName = node.getAttribute("id").asString();
			
			//load guis
			this.createGUI(nil, node);
			
			^this;
		}
		
		//----------------------------------------------------------------------------------------------//
		getLayoutItem { 
			arg itemName; 
			^this.layoutItems.at(itemName);
		}
		
		//----------------------------------------------------------------------------------------------//
		createGUI {
			arg parentGUI, parentNode;
			var currentChild;
			currentChild = parentNode.getFirstChild;
			while ( { currentChild != nil }, 
				{
					//switch on tag name
					switch(currentChild.getTagName)
					//------------------------------------------------//
					{
						"window"
					}
					{
						this.createWindow(currentChild);
					}
					//------------------------------------------------//
					{
						"button"
					}
					{
						this.createButton(parentGUI, currentChild);
					}
					//------------------------------------------------//
					{
						"slider"
					}
					{
						this.createSlider(parentGUI, currentChild);
					}
					//------------------------------------------------//
					{
						"compositeview"
					}
					{
						this.createCompositeView(parentGUI, currentChild);
					}
					//------------------------------------------------//
					{
						"hlayoutview"
					}
					{
						this.createHLayoutView(parentGUI, currentChild);
					}
					//------------------------------------------------//
					{
						"vlayoutview"
					}
					{
						this.createVLayoutView(parentGUI, currentChild);
					}
					//------------------------------------------------//
					{
						"popupmenu"
					}
					{
						this.createPopUpMenu(parentGUI, currentChild);
					}
					//------------------------------------------------//
					{
						"rangeslider"
					}
					{
						this.createRangeSlider(parentGUI, currentChild);
					}
					//------------------------------------------------//
					{
						"slider2d"
					}
					{
						this.createSlider2D(parentGUI, currentChild);
					}
					//------------------------------------------------//
					{
						"textfield"
					}
					{
						this.createTextField(parentGUI, currentChild);
					}
					//------------------------------------------------//
					{
						"statictext"
					}
					{
						this.createStaticText(parentGUI, currentChild);
					}
					//------------------------------------------------//
					{
						"listview"
					}
					{
						this.createListView(parentGUI, currentChild);
					}
					//------------------------------------------------//
					{
						"numberbox"
					}
					{
						this.createNumberBox(parentGUI, currentChild);
					}
					//------------------------------------------------//
					{
						"multisliderview"
					}
					{
						this.createMultiSliderView(parentGUI, currentChild);
					}
					//------------------------------------------------//
					{
						"envelopeview"
					}
					{
						this.createEnvelopeView(parentGUI, currentChild);
					}
					//------------------------------------------------//
					{
						"movieview"
					}
					{
						this.createMovieView(parentGUI, currentChild);
					}
					//------------------------------------------------//
					{
						"textview"
					}
					{
						this.createTextView(parentGUI, currentChild);
					}
					//------------------------------------------------//
					{
						"soundfileview"
					}
					{
						this.createSoundFileView(parentGUI, currentChild);
					};
					
					
					//get next child
					currentChild = currentChild.getNextSibling;
				}
			);
		}
				
		//----------------------------------------------------------------------------------------------//
		createWindow {
			arg node; 
			var handleBounds, handleBackground, resizable ,scroll, border, thisWindow, layoutID;
			
			//get attributes
			layoutID = node.getAttribute("id").asString();
			resizable = node.getAttribute("resizable").asString();
			scroll = node.getAttribute("scroll").asString();
			border = node.getAttribute("border").asString();
			
			//create function that accomodates for background color and alpha
			handleBackground = {
				arg currentWindow, currentNode;
				var tmpBackgroundColor, tmpBackgroundColorAlpha;
				
				//get values for the rect
				if(currentNode.getAttribute("background-color") == nil,
					{
						tmpBackgroundColor = 0.1;
					},
					{	
						tmpBackgroundColor = currentNode.getAttribute("background-color").asString();
					}
				);
				
				if(currentNode.getAttribute("background-color-alpha") == nil,
					{
						tmpBackgroundColorAlpha = 255;
					},
					{	
						tmpBackgroundColorAlpha = currentNode.getAttribute("background-color-alpha").asString();
					}
				);
				
				//set color
				thisWindow.view.background = this.getColorFromHex(tmpBackgroundColor, tmpBackgroundColorAlpha);
			};
			
			//create function to accomodate for bounds
			handleBounds = {
				arg currentWindow, currentNode;
				var tmpLeft, tmpTop, tmpWidth, tmpHeight;
				
				//get values for the rect
				if(currentNode.getAttribute("left") == nil,
					{
						tmpLeft = 0;
					},
					{	
						tmpLeft = currentNode.getAttribute("left").asInteger();
					}
				);
				
				if(currentNode.getAttribute("top") == nil,
					{
						tmpTop = 0;
					},
					{	
						tmpTop = currentNode.getAttribute("top").asInteger();
					}
				);
				
				if(currentNode.getAttribute("width") == nil,
					{
						tmpWidth = 100;
					},
					{	
						tmpWidth = currentNode.getAttribute("width").asInteger();
					}
				);
				
				if(currentNode.getAttribute("width") == nil,
					{
						tmpHeight = 100;
					},
					{	
						tmpHeight = currentNode.getAttribute("height").asInteger();
					}
				);
				
				//set bounds
				currentWindow.bounds_(Rect(tmpLeft, tmpTop, tmpWidth, tmpHeight));
				
			};
			
			//set attributes that are required at instantiation
			if(resizable == "true", { resizable = true; }, { resizable = false; });
			if(scroll == "true", { scroll = true; }, { scroll = false; });
			if(border == "true", { border = true; }, { border = false; });
			
			//create window
			thisWindow = GUI.window.new(resizable:resizable, scroll:scroll, border:border);
			
			//loop through attributes
			node.getAttributes().keysDo{
				arg key;
				
				//get value
				var thisValue = node.getAttribute(key);
				
				//switch on attribute name
				switch(key)
				//------------------------------------------------//
				{
					"name"
				}
				{
					thisWindow.name_(thisValue.asString());
				}
				//------------------------------------------------//
				{
					"left"
				}
				{
					handleBounds.value(thisWindow, node);
				}
				//------------------------------------------------//
				{
					"top"
				}
				{
					handleBounds.value(thisWindow, node);
				}
				//------------------------------------------------//
				{
					"width"
				}
				{
					handleBounds.value(thisWindow, node);
				}
				//------------------------------------------------//
				{
					"height"
				}
				{
					handleBounds.value(thisWindow, node);
				}
				//------------------------------------------------//
				{
					"background-color"
				}
				{
					handleBackground.value(thisWindow, node);
				}
				//------------------------------------------------//
				{
					"background-color-alpha"
				}
				{
					handleBackground.value(thisWindow, node);
				};
			};
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisWindow);
			
			//create other elements
			this.createGUI(thisWindow, node);
		}
		
		//----------------------------------------------------------------------------------------------//
		createButton{
			arg parentGUI, node;
			var left, top, width, height, currentChild, stateList, thisGUI, thisTextColor, thisTextColorAlpha, thisBackgroundColor, thisBackgroundColorAlpha, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			layoutID = node.getAttribute("id").asString();
			
			//create new list
			stateList = List.new(1);
		
			//create button
			thisGUI = GUI.button.new(parentGUI, Rect(left, top, width, height), border:false);
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
			
			currentChild = node.getFirstChild;
			while ( { currentChild != nil } , 
				{
			
					//get state text and background colors
					thisTextColor = currentChild.getAttribute("text-color").asString();
					thisTextColorAlpha = currentChild.getAttribute("text-color-alpha").asString();
					thisBackgroundColor = currentChild.getAttribute("background-color").asString();
					thisBackgroundColorAlpha = currentChild.getAttribute("background-color-alpha").asString();
					
					//add state to the collection
					stateList.add([currentChild.getAttribute("label").asString(), this.getColorFromHex(thisTextColor, thisTextColorAlpha), this.getColorFromHex(thisBackgroundColor, thisBackgroundColorAlpha)]);
					
					//get next child
					currentChild = currentChild.getNextSibling;
				}
			);
		
			//add states
			thisGUI.states_(stateList.asArray());
		
		}
		
		//----------------------------------------------------------------------------------------------//
		createSlider{
			arg parentGUI, node;
			var left, top, width, height, knobColor, knobColorAlpha, focusColor, focusColorAlpha, currentChild, thisGUI, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			
			knobColor = node.getAttribute("knob-color").asString();
			knobColorAlpha = node.getAttribute("knob-color-alpha").asString();
			
			focusColor = node.getAttribute("focus-color").asString();
			focusColorAlpha = node.getAttribute("focus-color-alpha").asString();
			
			layoutID = node.getAttribute("id").asString();
			
			//create button
			thisGUI = GUI.slider.new(parentGUI, Rect(left, top, width, height));
			
			//set focus color
			thisGUI.focusColor_(this.getColorFromHex(focusColor, focusColorAlpha));
			
			//set knob color
			thisGUI.knobColor_(this.getColorFromHex(knobColor, knobColorAlpha));
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
		}
		
		//----------------------------------------------------------------------------------------------//
		createSoundFileView{
			arg parentGUI, node;
			var left, top, width, height, currentChild, thisGUI, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			layoutID = node.getAttribute("id").asString();
		
			//create gui
			thisGUI = GUI.soundFileView.new(parentGUI, Rect(left, top, width, height));
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
		}
		
		//----------------------------------------------------------------------------------------------//
		createCompositeView{
			arg parentGUI, node;
			var left, top, width, height,backgroundColor, backgroundColorAlpha, visible, thisGUI, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			visible = node.getAttribute("visible").asString();
			layoutID = node.getAttribute("id").asString();
			backgroundColor = node.getAttribute("background-color").asString();
			backgroundColorAlpha = node.getAttribute("background-color-alpha").asString();
			
			if(visible == "true", { visible = true; }, { visible = false; });
			
			//create gui
			thisGUI = GUI.compositeView.new(parentGUI, Rect(left, top, width, height));
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
			
			//set background color
			thisGUI.background = this.getColorFromHex(backgroundColor, backgroundColorAlpha);

			//set visibility
			thisGUI.visible = visible;
			
			//process children
			this.createGUI(thisGUI, node);
		}
		
		//----------------------------------------------------------------------------------------------//
		createHLayoutView{
			arg parentGUI, node;
			var left, top, width, height, resize, thisGUI, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			resize = node.getAttribute("resize").asInteger();
			layoutID = node.getAttribute("id").asString();
		
			//create gui
			thisGUI = GUI.hLayoutView.new(parentGUI, Rect(left, top, width, height));
			
			//set resize
			thisGUI.resize = resize;
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
			
			//this is for testing - we will get styling working later
			thisGUI.background = Gradient(Color.rand,Color.rand);
			
			//process children
			this.createGUI(thisGUI, node);
		}
		
		//----------------------------------------------------------------------------------------------//
		createVLayoutView{
			arg parentGUI, node;
			var left, top, width, height, resize, thisGUI, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			resize = node.getAttribute("resize").asInteger();
			layoutID = node.getAttribute("id").asString();
		
			//create gui
			thisGUI = GUI.vLayoutView.new(parentGUI, Rect(left, top, width, height));
			
			//set resize
			thisGUI.resize = resize;
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
			
			//this is for testing - we will get styling working later
			thisGUI.background = Gradient(Color.rand,Color.rand);
			
			//process children
			this.createGUI(thisGUI, node);
		}
		
		//----------------------------------------------------------------------------------------------//
		createPopUpMenu{
			arg parentGUI, node;
			var left, top, width, height,backgroundColor,backgroundColorAlpha, currentChild, itemList, thisGUI, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			backgroundColor = node.getAttribute("background-color").asString();
			backgroundColorAlpha = node.getAttribute("background-color-alpha").asString();
			layoutID = node.getAttribute("id").asString();
			
			
			//create new list
			itemList = List.new(1);
		
			//create button
			thisGUI = GUI.popUpMenu.new(parentGUI, Rect(left, top, width, height));
			
			//set color
			thisGUI.background_(this.getColorFromHex(backgroundColor, backgroundColorAlpha));
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
			
			currentChild = node.getFirstChild;
			while ( { currentChild != nil } , 
				{
					
					//add state to the collection
					itemList.add(currentChild.getAttribute("value").asString());
					
					//get next child
					currentChild = currentChild.getNextSibling;
				}
			);
		
			//add items
			thisGUI.items = itemList.asArray();
		
		}
		
		//----------------------------------------------------------------------------------------------//
		createRangeSlider{
			arg parentGUI, node;
			var left, top, width, height, currentChild, thisGUI, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			layoutID = node.getAttribute("id").asString();
		
			//create gui
			thisGUI = GUI.rangeSlider.new(parentGUI, Rect(left, top, width, height));
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
		}
		
		//----------------------------------------------------------------------------------------------//
		createSlider2D{
			arg parentGUI, node;
			var left, top, width, height, currentChild, thisGUI, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			layoutID = node.getAttribute("id").asString();
		
			//create gui
			thisGUI = GUI.slider2D.new(parentGUI, Rect(left, top, width, height));
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
		}
		
		//----------------------------------------------------------------------------------------------//
		createTextField{
			arg parentGUI, node;
			var left, top, width, height, defaultString, currentChild, thisGUI, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			defaultString = node.getAttribute("string").asString();
			layoutID = node.getAttribute("id").asString();
		
			//create gui
			thisGUI = GUI.textField.new(parentGUI, Rect(left, top, width, height));
			
			//set string
			thisGUI.string = defaultString;
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
		}
		
		//----------------------------------------------------------------------------------------------//
		createListView{
			arg parentGUI, node;
			var left, top, width, height,selectedStringColor,selectedStringColorAlpha, focusColor, focusColorAlpha, backgroundColor, backgroundColorAlpha, hiliteColor, hiliteColorAlpha, currentChild, itemList, thisGUI, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			backgroundColor = node.getAttribute("background-color").asString();
			backgroundColorAlpha = node.getAttribute("background-color-alpha").asString();
			hiliteColor = node.getAttribute("hilite-color").asString();
			hiliteColorAlpha = node.getAttribute("hilite-color-alpha").asString();
			selectedStringColor = node.getAttribute("selected-string-color").asString();
			selectedStringColorAlpha = node.getAttribute("selected-string-color-alpha").asString();
			
			focusColor = node.getAttribute("focus-color").asString();
			focusColorAlpha = node.getAttribute("focus-color-alpha").asString();
			
			layoutID = node.getAttribute("id").asString();
			
			//create new list
			itemList = List.new(1);
		
			//create button
			thisGUI = GUI.listView.new(parentGUI, Rect(left, top, width, height));
			
			//set color
			thisGUI.background_(this.getColorFromHex(backgroundColor, backgroundColorAlpha));
			
			//set hilite color
			thisGUI.hiliteColor_(this.getColorFromHex(hiliteColor, backgroundColorAlpha));
			
			//set focus color
			thisGUI.focusColor_(this.getColorFromHex(focusColor, focusColorAlpha));
			
			//set selected string color
			thisGUI.selectedStringColor_(this.getColorFromHex(selectedStringColor, selectedStringColorAlpha));
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
			
			currentChild = node.getFirstChild;
			while ( { currentChild != nil } , 
				{
					
					//add state to the collection
					itemList.add(currentChild.getAttribute("value").asString());
					
					//get next child
					currentChild = currentChild.getNextSibling;
				}
			);
		
			//add items
			thisGUI.items = itemList.asArray();
		
		}
		
		//----------------------------------------------------------------------------------------------//
		createStaticText{
			arg parentGUI, node;
			var left, top, width, height, defaultString, currentChild, thisGUI, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			defaultString = node.getAttribute("string").asString();
			layoutID = node.getAttribute("id").asString();
			
			//create gui
			thisGUI = GUI.staticText.new(parentGUI, Rect(left, top, width, height));
			
			//set string
			thisGUI.string = defaultString;
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
		}
		
		//----------------------------------------------------------------------------------------------//
		createNumberBox{
			arg parentGUI, node;
			var left, top, width, height, defaultValue, currentChild, thisGUI, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			defaultValue = node.getAttribute("value").asInteger();
			layoutID = node.getAttribute("id").asString();
			
			//create gui
			thisGUI = GUI.numberBox.new(parentGUI, Rect(left, top, width, height));
			
			//set string
			thisGUI.value = defaultValue;
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
		}
		
		//----------------------------------------------------------------------------------------------//
		createMultiSliderView{
			arg parentGUI, node;
			var left, top, width, height, currentChild, thisGUI, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			layoutID = node.getAttribute("id").asString();
			
			//create gui
			thisGUI = GUI.multiSliderView.new(parentGUI, Rect(left, top, width, height));
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
		}
		
		//----------------------------------------------------------------------------------------------//
		createEnvelopeView{
			arg parentGUI, node;
			var left, top, width, height, currentChild, thisGUI, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			layoutID = node.getAttribute("id").asString();
			
			//create gui
			thisGUI = GUI.envelopeView.new(parentGUI, Rect(left, top, width, height));
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
		}
		
		//----------------------------------------------------------------------------------------------//
		createMovieView{
			arg parentGUI, node;
			var left, top, width, height, currentChild, thisGUI, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			layoutID = node.getAttribute("id").asString();
			
			//create gui
			thisGUI = GUI.movieView.new(parentGUI, Rect(left, top, width, height));
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
		}
		
		//----------------------------------------------------------------------------------------------//
		createTextView{
			arg parentGUI, node;
			var left, top, width, height, defaultValue, currentChild, thisGUI, layoutID;
		
			//get attributes
			left = node.getAttribute("left").asInteger();
			top = node.getAttribute("top").asInteger();
			width = node.getAttribute("width").asInteger();
			height = node.getAttribute("height").asInteger();
			defaultValue = node.getAttribute("string").asString();
			layoutID = node.getAttribute("id").asString();
			
			//create gui
			thisGUI = GUI.textView.new(parentGUI, Rect(left, top, width, height));
			
			//set string
			thisGUI.string_(defaultValue);
			
			//add to layoutItems
			this.layoutItems.add(layoutID -> thisGUI);
		}
		
		//----------------------------------------------------------------------------------------------//
		getColorFromHex{
			arg hexName = "000000", alpha = 255;
			var r, g, b, number;
			
			//make sure the hexname/apha are not returning nil
			try {
				number = ("16x" ++ hexName.asString()).interpret;
				alpha = ("16x" ++ alpha.asString()).interpret;
				b = number % 256;
				g = ((number / 256) % 256).floor;
				r = ((number / 65536) % 256).floor; 
				^Color.new255(r,g,b, alpha);
			}
			{
				|error| 
				"in here".postln;
				//error.postln; 
				^Color.new255(255, 255, 255, 255)
			};
		}
		
	}
	