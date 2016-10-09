function f_frameStyleResize(targObj)
{
	var targWin = targObj.parent.document.all[targObj.name];
	if(targWin != null) 
	{
		var HeightValue = targObj.document.body.scrollHeight;
		if(HeightValue < 150)
		{
			HeightValue = 150;
		}
		targWin.style.pixelHeight = HeightValue + 5;
		//alert("HeightValue:" + targWin.style.pixelHeight);
		
		var WidthValue = targObj.document.body.scrollWidth;
		if(WidthValue < 300)
		{
			WidthValue = 300;
		}
		targWin.style.pixelWidth = WidthValue;
	}
}

function f_iframeResize()
{
	//alert("alert size");
	bLoadComplete = true; 
	f_frameStyleResize(self);
	var parent = self.parent;
	//alert("parent:" + parent);
	if(parent != null) {
		f_frameStyleResize(parent);
	}
}

var bLoadComplete = false;
window.onload = f_iframeResize;
