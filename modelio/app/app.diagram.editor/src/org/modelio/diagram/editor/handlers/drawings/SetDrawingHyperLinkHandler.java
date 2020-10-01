/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.diagram.editor.handlers.drawings;

import java.util.function.Consumer;
import javax.inject.Named;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.drawings.core.IGmDrawing;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLayer;
import org.modelio.platform.core.picking.IModelioPickingService;
import org.modelio.platform.model.ui.swt.SelectionHelper;
import org.modelio.platform.model.ui.swt.textelement.TextElement;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.ui.dialog.ModelioDialog;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Handler to set the hyperlink of drawings..
 * 
 * @author phv
 */
@objid ("a8e3fbbb-0c48-4c69-b6a2-a4e62e3343df")
public class SetDrawingHyperLinkHandler {
    /**
     * @param selection the Eclipse selection
     * @param pickingService the picking service
     */
    @objid ("485d8402-e791-4ddc-a950-a23c65f5ce61")
    @Execute
    public void execute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection, @Named (IServiceConstants.ACTIVE_SHELL) Shell parentShell, IProjectService projSvc, IModelioPickingService pickingService) {
        GraphicalEditPart ep = filterSelection(selection);
        
        ICoreSession session = projSvc.getSession();
        MRef initial = getDrawing(ep).getHyperLink();
        MObject initialEl = initial == null ? null : session.getModel().findByRef(initial);
        
        SetDrawingHyperLinkDialog dlg = new SetDrawingHyperLinkDialog(
                parentShell, 
                session, 
                pickingService, 
                initialEl,
                choosen -> execute(ep, choosen));
        
        dlg.open();
    }

    @objid ("cb264e13-2be1-4fd0-bc8a-c2af7f1f5dfd")
    private GraphicalEditPart filterSelection(ISelection sel) {
        return SelectionHelper.toStream(sel, GraphicalEditPart.class)
                .filter(ep -> isUserEditable(ep) && ep.getModel() instanceof IGmDrawing && !(ep.getModel() instanceof IGmDrawingLayer))
                .findFirst()
                .orElse(null);
    }

    @objid ("a0f12493-a1e6-4538-9151-020866c7c793")
    protected void execute(GraphicalEditPart ep, MObject selected) {
        IGmDrawing gm = (IGmDrawing) ep.getModel();
        
        Command cmd = new Command("Set hyperlink to "+ selected) {
            @Override
            public void execute() {
                gm.setHyperLink(selected == null ? null : new MRef(selected));
            }
            
            @Override
            public boolean canExecute() {
                return gm.isUserEditable();
            }
        };
        
        EditPartViewer viewer = ep.getViewer();
        if (viewer != null && cmd.canExecute()) {
            viewer.getEditDomain().getCommandStack().execute(cmd);
        } else if (viewer == null) {
            DiagramEditor.LOG.warning("%s : could not reach a valid EditPartViewer from %s", getClass().getSimpleName(), ep);
        }
    }

    @objid ("2b7b7d5b-9538-4960-b3f5-625455c363c4")
    private boolean isUserEditable(EditPart editPart) {
        return ((IGmObject) editPart.getModel()).isUserEditable();
    }

    @objid ("0c441450-ee08-4bd2-b7cf-9960169e877e")
    @CanExecute
    public boolean canExecute(@Named (IServiceConstants.ACTIVE_SELECTION) ISelection selection) {
        GraphicalEditPart selectedEditParts = filterSelection(selection);
        return selectedEditParts != null;
    }

    @objid ("1070b2e8-8c3f-43eb-900a-43bf25381993")
    protected static IGmDrawing getDrawing(GraphicalEditPart ep) {
        return (IGmDrawing) ep.getModel();
    }

    @objid ("7d91c9d2-8151-43b2-b59f-d002e1ab6aea")
    private static class SetDrawingHyperLinkDialog extends ModelioDialog {
        @objid ("7bcd24f3-cac1-4b30-ac00-4e30ae0c4b3f")
        private Consumer<MObject> onOk;

        @objid ("72eeb563-ac2c-43eb-9a08-01c917cb4eb2")
        private TextElement elementText;

        @objid ("1d52dc40-c976-4b26-ac20-a1ad7a4cba99")
        private Composite composite;

        @objid ("a8e74444-379c-47c1-83dd-49bf51becd70")
        private final ICoreSession session;

        @objid ("507ae999-7e37-4f8d-9ddb-f5d75742e5e1")
        private final IModelioPickingService pickingService;

        @objid ("bded2b68-9732-48b6-8baa-5e60940f7065")
        private MObject choosen;

        @objid ("eaeb38c5-c16e-4ad2-8733-ee3e0440509c")
        public SetDrawingHyperLinkDialog(Shell parentShell, ICoreSession session, IModelioPickingService pickingService, MObject initial, Consumer<MObject> onOk) {
            super(parentShell);
            this.session = session;
            this.pickingService = pickingService;
            this.choosen = initial;
            this.onOk = onOk;
            
            setBlockOnOpen(false);
            setShellStyle(getShellStyle() & ~(SWT.APPLICATION_MODAL | SWT.SYSTEM_MODAL | SWT.PRIMARY_MODAL));
        }

        @objid ("bb930eb8-85b5-4ea7-832a-f42448fc4f40")
        @Override
        public void init() {
            getShell().setText(DiagramEditor.I18N.getMessage("Gui.SetDrawingHyperLinkDialog.ShellTitle"));
            
            setTitle(DiagramEditor.I18N.getMessage("Gui.SetDrawingHyperLinkDialog.Title"));
            setMessage(DiagramEditor.I18N.getMessage("Gui.SetDrawingHyperLinkDialog.Message"));
            setBlockOnOpen(false);
        }

        @objid ("21314bb3-a51d-4701-a12c-e7483e4a03b0")
        @Override
        public void addButtonsInButtonBar(final Composite parent) {
            createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
            createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
        }

        @objid ("063017ad-6686-46ca-9458-f26153ee6bad")
        @Override
        protected void okPressed() {
            super.okPressed();
            this.onOk.accept(this.elementText.getValue());
        }

        @objid ("f3c335a1-5916-40d8-80ac-7a47e526e42c")
        @Override
        protected Point getInitialSize() {
            return new Point(convertWidthInCharsToPixels(80), convertHeightInCharsToPixels(15));
        }

        @objid ("aececed0-3b6f-4761-b9a6-8fd336fa228f")
        @Override
        public Control createContentArea(final Composite parent) {
            this.composite = new Composite(parent, SWT.BORDER);
            this.composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            this.composite.setLayout(new GridLayout(1, true));
            
            // Diagram type browser
            this.elementText = new TextElement(this.composite, SWT.BORDER, true);
            this.elementText.activateCompletion(this.session);
            this.elementText.activateDragAndDrop(this.session);
            this.elementText.activatePicking(this.pickingService);
            this.elementText.getAcceptedMetaclasses().add(this.session.getMetamodel().getMClass(MObject.class));
            this.elementText.setValue(this.choosen);
            
            // Layout the form
            this.elementText.getTextControl().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
            return this.composite;
        }

        @objid ("b51201f4-b056-45d9-99c7-0e5787f77e37")
        public MObject getChoosen() {
            return this.choosen;
        }

    }

}
