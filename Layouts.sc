/*
	License: http://www.gnu.org/licenses/gpl-3.0.txt
	Author: Sam Ingrassia - sam@samingrassia.com
*/
	Layouts {
		classvar <>layouts;

		*load{
			arg fileName, onLoadFunc;
			
			var currentDocment;
			
			//determine whether the layouts property has a dictionary in it
			if(Layouts.layouts.isKindOf(Dictionary),
				{
					//do nothing
				},
				{
					Layouts.layouts = Dictionary.new;
				}
			);
			
			//load file
			currentDocment = DOMDocument.new(fileName);
			
			//load up layouts
			currentDocment.getDocumentElement.getElementsByTagName("layouts").do(
				{ 
					arg tag, index;
					
					var currentChild = tag.getFirstChild;
					
					while ( { currentChild != nil }, 
						{
							
							//switch on tag name
							switch(currentChild.getTagName)
							//------------------------------------------------//
							{
								"layout"
							}
							{
								
								//create new layout and add it to layouts	
								Layouts.layouts.add(currentChild.getAttribute("id").asString() -> Layout.new);
								
								//load layout
								Layouts.getLayout(currentChild.getAttribute("id").asString()).load(currentChild);
								
								//execute onload function
								onLoadFunc.value(Layouts.getLayout(currentChild.getAttribute("id").asString()));
							}
							//------------------------------------------------//
							{
								"overlay"
							}
							{
								var thisLayout, thisLayoutItem;
								
								//get the layout
								thisLayout = Layouts.getLayout(currentChild.getAttribute("layout").asString());
								
								//get the layout item
								thisLayoutItem = thisLayout.getLayoutItem(currentChild.getAttribute("id").asString());
								
								{
									//create the gui
									thisLayout.createGUI(thisLayoutItem, currentChild);
								}.defer;
								
								//execute onload function
								onLoadFunc.value(Layouts.getLayout(currentChild.getAttribute("layout").asString()));
							};
						
							//get next child
							currentChild = currentChild.getNextSibling;
						}
					);
				}
			);
			
		}
		
		*addLayout{
			arg layoutName, thisLayout;
			
			//determine whether the layouts property has a dictionary in it
			if(Layouts.layouts.isKindOf(Dictionary),
				{
					//do nothing
				},
				{
					Layouts.layouts = Dictionary.new;
				}
			);
			
			//add layout to Layouts
			Layouts.layouts.add(layoutName -> thisLayout);
		}
		
		*getLayout{
			arg layoutName;
			
			//determine whether the layouts property has a dictionary in it
			if(Layouts.layouts.isKindOf(Dictionary),
				{
					//do nothing
				},
				{
					Layouts.layouts = Dictionary.new;
				}
			);
			
			//return layout
			^Layouts.layouts.at(layoutName);
		}
		
	}