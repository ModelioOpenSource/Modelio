/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.edition.dialogs.dialog.panels.operation.signature;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.edition.dialogs.dialog.panels.operation.IOperationPropertyModel;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.ui.panel.IPanelProvider;

/**
 * Reusable panel to preview an operation's signature.
 * <p>
 * Needs an {@link IOperationPropertyModel} as input.
 * </p>
 */
@objid ("093046bf-e6b1-43f3-b586-0ebfb9763635")
public class OperationSignaturePanel implements IPanelProvider {
    @objid ("3cfe2de5-29ab-46ce-8200-bf30d59ee2bb")
    private IOperationPropertyModel opModel;

    @objid ("fbf7b81c-ea8a-4a03-88e3-bb5935dd25e4")
    private Composite container;

    @objid ("160748a7-2e1e-4ce0-92d0-de2780afd005")
    private Label previewLabel;

    @objid ("8676d0f8-9df9-499c-96d4-10f77897c3ac")
     Label previewIcon;

    @objid ("cd5bb156-2e3d-4087-b592-62a0408245a4")
    @Override
    public Object createPanel(Composite parent) {
        this.container = new Composite(parent, SWT.NONE);
        GridLayout gl = new GridLayout(2, false);
        gl.marginHeight = 0;
        gl.marginWidth = 0;
        gl.horizontalSpacing = 4;
        
        this.container.setLayout(gl);
        
        this.previewIcon = new Label(this.container, SWT.NONE);
        this.previewIcon.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
        
        this.previewLabel = new Label(this.container, SWT.NONE);
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, false);
        this.previewLabel.setLayoutData(gd);
        return this.container;
    }

    @objid ("2e54ad24-fb66-478e-8646-269707874f82")
    @Override
    public Object getPanel() {
        return this.container;
    }

    @objid ("7cc5b806-f7e5-4217-a21e-2e965d6906eb")
    @Override
    public void setInput(Object input) {
        if (input instanceof IOperationPropertyModel) {
            this.opModel = (IOperationPropertyModel) input;
            this.previewLabel.setText(new OpSignatureBuilder().getSignature(this.opModel));
        
            if (this.opModel.isAbstract()) {
                LocalResourceManager res = new LocalResourceManager(JFaceResources.getResources(), this.container);
                Font italicFont = res.createFont(FontDescriptor.createFrom(this.container.getFont()).withStyle(SWT.ITALIC));
                this.previewLabel.setFont(italicFont);
            } else {
                this.previewLabel.setFont(this.container.getFont());
            }
            this.previewIcon.setImage(ElementImageService.getIcon(this.opModel.getOperation()));
        
        } else {
            this.opModel = null;
            this.previewLabel.setText("");
            this.previewIcon.setImage(null);
        }
    }

    @objid ("56aea30f-f43c-4fa8-a4e1-58ed7122d111")
    @Override
    public Object getInput() {
        return this.opModel;
    }

    @objid ("723f1c93-1215-441d-be1b-30f7f23df999")
    @Override
    public boolean isRelevantFor(Object obj) {
        return true;
    }

    @objid ("84d0dd54-2953-409c-a0ac-bea2775648c6")
    @Override
    public String getHelpTopic() {
        return null;
    }

    @objid ("a625e0ef-701a-41ff-907e-41a47060bbbe")
    @Override
    public void dispose() {
        // nothing to do
    }

    @objid ("c1557bc0-27ae-4791-acfd-53db6077833c")
    private static class OpSignatureBuilder {
        @objid ("db3601d9-040a-4db3-95c4-1002e6665369")
        public String getSignature(IOperationPropertyModel opModel) {
            StringBuilder s = new StringBuilder();
            
            s.append(getVisibility(opModel));
            s.append(" ");
            s.append(opModel.getName());
            s.append(" (");
            
            IOParamSignatureBuilder pb = new IOParamSignatureBuilder();
            for (int i = 0; i < opModel.getIOParameterSize(); i++) {
                s.append(pb.getSignature(opModel.getParameter(i)));
                if (i != opModel.getIOParameterSize() - 1) {
                    s.append(", ");
                }
            }
            
            s.append(")");
            
            Parameter returnParameter = opModel.getReturnParameter();
            if (returnParameter != null) {
                s.append(pb.getSignature(returnParameter));
            }
            return s.toString();
        }

        @objid ("f73af903-8374-49f9-86b9-aaf6aeb0bfc3")
        private String getVisibility(IOperationPropertyModel opModel) {
            switch (opModel.getVisibility()) {
            case PUBLIC:
                return "+";
            case PACKAGEVISIBILITY:
                return "~";
            case PRIVATE:
                return "-";
            case PROTECTED:
                return "#";
            case VISIBILITYUNDEFINED:
                return "";
            default:
                return "";
            }
        }

    }

    @objid ("5d9c0776-00a6-4d32-9cbc-eed33fdb01b2")
    private static class IOParamSignatureBuilder {
        @objid ("c6f4cf7d-6aea-4f0f-91b7-16412c521933")
        public String getSignature(Parameter p) {
            StringBuilder s = new StringBuilder();
            
            // direction name : type multiplicity = default
            if (p.getComposed() != null) {
                // name and direction only for IO parameters
                s.append(p.getName());
                s.append(" ");
                s.append(getDirection(p));
                s.append(" ");
            }
            s.append(": ");
            s.append((p.getType() != null) ? p.getType().getName() : "<notype>");
            
            // only show multiplicity if not 1 for both min and max
            s.append(getMultiplicity(p));
            return s.toString();
        }

        @objid ("dea7f209-3902-406d-a760-b511bf6ba93f")
        private String getDirection(Parameter p) {
            switch (p.getParameterPassing()) {
            case IN:
                return "in";
            case INOUT:
                return "inout";
            case OUT:
                return "out";
            default:
                return "";
            }
        }

        @objid ("e2d24868-488b-4108-b6a7-4fb649240dc8")
        private String getMultiplicity(Parameter param) {
            StringBuilder multiplicity = new StringBuilder();
            
            String multiplicityMinStr = param.getMultiplicityMin();
            String multiplicityMaxStr = param.getMultiplicityMax();
            String separator = "";
            
            if (multiplicityMinStr.equals("1") && multiplicityMaxStr.equals("1")) {
                return multiplicity.toString();
            }
            
            if (!multiplicityMinStr.equals("") || !multiplicityMaxStr.equals("")) {
                multiplicity.append(" [");
                // multiplicity.append("[");
                if (multiplicityMinStr.equals(multiplicityMaxStr)) {
                    multiplicity.append(multiplicityMinStr);
                } else if (multiplicityMinStr.equals("0") && multiplicityMaxStr.equals("*")) {
                    multiplicity.append("*");
                } else {
                    if (!multiplicityMinStr.equals("") && !multiplicityMaxStr.equals("")) {
                        separator = "..";
                    }
            
                    multiplicity.append(multiplicityMinStr);
                    multiplicity.append(separator);
                    multiplicity.append(multiplicityMaxStr);
                }
                multiplicity.append("]");
            }
            return multiplicity.toString();
        }

    }

}
