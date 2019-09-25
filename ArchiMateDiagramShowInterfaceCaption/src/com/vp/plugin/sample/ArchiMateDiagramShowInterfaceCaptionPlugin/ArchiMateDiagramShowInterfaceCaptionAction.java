package com.vp.plugin.sample.ArchiMateDiagramShowInterfaceCaptionPlugin;


import com.vp.plugin.ApplicationManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.diagram.IArchiMateDiagramUIModel;
import com.vp.plugin.diagram.IDiagramUIModel;
import com.vp.plugin.model.IProject;

public class ArchiMateDiagramShowInterfaceCaptionAction implements VPActionController {

	@Override
	public void performAction(VPAction arg0) {
		// Retrieve the project
		IProject project = ApplicationManager.instance().getProjectManager().getProject();
		
		// Walk through all diagrams in the project and see is it an ArchiMate diagram
		IDiagramUIModel[] diagrams = project.toDiagramArray();
		if (diagrams != null && diagrams.length > 0) {
			IDiagramUIModel openingDiagram = null;
			for(IDiagramUIModel diagram: diagrams) {
				if (diagram instanceof IArchiMateDiagramUIModel) {
					// Check is the diagram already opened
					if (diagram.isOpened()) {
						openingDiagram = diagram;
					}
					// Open the ArchiMate diagram
					ApplicationManager.instance().getDiagramManager().openDiagram(diagram);
					// Set option to show the interface
					((IArchiMateDiagramUIModel) diagram).setShowActivityStateNodeCaption(524287);
					// close the diagram
					diagram.closeDiagram();
				}
			}
			// Reopen the diagram if it previously already opened
			if (openingDiagram != null) {
				ApplicationManager.instance().getDiagramManager().openDiagram(openingDiagram);				
			}
		}		
	}

	@Override
	public void update(VPAction arg0) {
		// TODO Auto-generated method stub
		
	}

}
