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
package org.modelio.edition.dialogs.dialog.panels.operation.signature;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.modelio.edition.dialogs.dialog.panels.operation.IOperationPropertyModel;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.platform.ui.CoreFontRegistry;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.panel.IPanelProvider;

/**
 * Reusable panel to preview an operation's signature.
 * <p>
 * Needs an {@link IOperationPropertyModel} as input.
 * </p>
 */
@objid ("093046bf-e6b1-43f3-b586-0ebfb9763635")
public class OperationSignaturePanel implements IPanelProvider {
    @objid ("7ddf6b52-9524-4d9e-8f9b-836629c16b0d")
    private Composite container;

    @objid ("963ab53c-0f7b-4a75-bea4-fe09dd2e187a")
    private StyledText previewLabel;

    @objid ("b82541ff-af0b-4f2d-a4b5-3dc95c946283")
    Label previewIcon;

    @objid ("3cfe2de5-29ab-46ce-8200-bf30d59ee2bb")
    private IOperationPropertyModel opModel;

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
        
        this.previewLabel = new StyledText(this.container, SWT.WRAP);
        GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
        this.previewLabel.setLayoutData(gd);
        this.previewLabel.setBackground(this.previewLabel.getParent().getBackground());
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
        
            StyledString signature = new OpSignatureBuilder(this.opModel).getSignature();
            StyleRange[] styleRanges = signature.getStyleRanges();
        
            this.previewLabel.setText(signature.getString());
            this.previewLabel.setStyleRanges(styleRanges);
        
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
        // As the label can wrap or unwrap depending on its contents length, fire a layout on the container parent in order to adapt the layout to the new size.
        this.container.getParent().layout(true, true);
        
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
        @objid ("bdc3f8ef-efac-48dc-8f0a-df9acb80415f")
        private Styler nameStyler;

        @objid ("9dacf6b9-5fea-4fab-bc2b-51e9a3ec40d4")
        private Styler syntaxStyler;

        @objid ("9a92b14e-e326-4f6c-83de-5a63dd64205a")
        private Styler typeStyler;

        @objid ("2e910848-d702-4bed-a57c-40875dae76d2")
        private Styler returnTypeStyler;

        @objid ("73fa4e9f-c751-421a-b40e-12fe6520ddf2")
        private static final Font normalFont = CoreFontRegistry.getFont(Display.getCurrent().getSystemFont().getFontData());

        @objid ("c7a8afed-9adc-4dd4-b5ac-3377f66a55ac")
        private static final Font italicFont = CoreFontRegistry.getModifiedFont(normalFont, SWT.ITALIC, 1.0f);

        @objid ("1a0c307c-c4ff-40b8-8179-49fb626b711e")
        private static final Font boldFont = CoreFontRegistry.getModifiedFont(normalFont, SWT.BOLD, 1.0f);

        @objid ("c514599d-6a5a-4b65-8bca-6a307dd0a86b")
        private static final Font italicBoldFont = CoreFontRegistry.getModifiedFont(normalFont, SWT.BOLD | SWT.ITALIC, 1.0f);

        @objid ("9692a6e5-181f-4236-9f69-8e708b975da5")
        private IOperationPropertyModel opModel;

        @objid ("db3601d9-040a-4db3-95c4-1002e6665369")
        public StyledString getSignature() {
            StyledString ss = new StyledString();
            
            ss.append(getVisibility(this.opModel), this.syntaxStyler);
            ss.append(" ");
            ss.append(this.opModel.getName(), this.nameStyler);
            ss.append(" (", this.syntaxStyler);
            
            // StringBuilder s = new StringBuilder();
            // s.append(getVisibility(opModel));
            // s.append(" ");
            // s.append(opModel.getName());
            // s.append(" (");
            
            IOParamSignatureBuilder pb = new IOParamSignatureBuilder(this);
            for (int i = 0; i < this.opModel.getIOParameterSize(); i++) {
                ss.append(pb.getSignature(this.opModel.getParameter(i)));
                if (i != this.opModel.getIOParameterSize() - 1) {
                    ss.append(", ", this.syntaxStyler);
                }
            }
            
            ss.append(")");
            
            Parameter returnParameter = this.opModel.getReturnParameter();
            if (returnParameter != null) {
                ss.append(pb.getSignature(returnParameter));
            }
            return ss;
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

        @objid ("7cbab773-07d5-4623-b399-10edf4eb4e1c")
        public  OpSignatureBuilder(IOperationPropertyModel opModel) {
            this.opModel = opModel;
            
            this.nameStyler = new Styler() {
                @Override
                public void applyStyles(final TextStyle textStyle) {
                    textStyle.font = opModel.isAbstract() ? italicBoldFont : boldFont;
                    textStyle.underline = opModel.isClass();
                    textStyle.foreground = UIColor.BLUE;
                }
            };
            this.syntaxStyler = new Styler() {
                @Override
                public void applyStyles(final TextStyle textStyle) {
                    textStyle.font = opModel.isAbstract() ? italicBoldFont : boldFont;
                    textStyle.underline = opModel.isClass();
                    textStyle.foreground = UIColor.GRAY;
                }
            };
            this.typeStyler = new Styler() {
                @Override
                public void applyStyles(final TextStyle textStyle) {
                    textStyle.font = opModel.isAbstract() ? italicBoldFont : boldFont;
                    textStyle.underline = opModel.isClass();
                    textStyle.foreground = UIColor.GREEN;
                }
            };
            this.returnTypeStyler = new Styler() {
                @Override
                public void applyStyles(final TextStyle textStyle) {
                    textStyle.font = opModel.isAbstract() ? italicBoldFont : boldFont;
                    textStyle.underline = opModel.isClass();
                    textStyle.foreground = UIColor.BROWN;
                }
            };
            
        }

        @objid ("cb14b989-685c-4c9d-a210-1fd072a19c61")
        public Styler getNameStyler() {
            return this.nameStyler;
        }

        @objid ("4a2c7440-c20d-4bd3-bfcc-0a78f600dd32")
        public Styler getSyntaxStyler() {
            return this.syntaxStyler;
        }

        @objid ("78af6a1a-f8c1-4728-95f0-eac11f6acd2e")
        public Styler getTypeStyler() {
            return this.typeStyler;
        }

        @objid ("c9d520ad-85cd-4c1a-9094-feb33cd43c8b")
        public Styler getReturnTypeStyler() {
            return this.returnTypeStyler;
        }

    }

    @objid ("5d9c0776-00a6-4d32-9cbc-eed33fdb01b2")
    private static class IOParamSignatureBuilder {
        @objid ("477dbbc5-982a-4ddd-a10d-d77ec5eac35f")
        private OpSignatureBuilder opSignatureBuilder;

        @objid ("c6f4cf7d-6aea-4f0f-91b7-16412c521933")
        public StyledString getSignature(Parameter p) {
            StyledString s = new StyledString();
            
            boolean isReturnParameter = (p.getComposed() == null);
            
            
            // direction name : type multiplicity = default
            if (! isReturnParameter) {
                // name and direction only for IO parameters
                s.append(p.getName(), this.opSignatureBuilder.getNameStyler());
                s.append(" ", this.opSignatureBuilder.getSyntaxStyler());
                s.append(passingModeAsString(p), this.opSignatureBuilder.getSyntaxStyler());
                s.append(" ", this.opSignatureBuilder.getSyntaxStyler());
            }
            s.append(": ", this.opSignatureBuilder.getSyntaxStyler());
            s.append((p.getType() != null) ? p.getType().getName() : "<notype>",
                    isReturnParameter ? this.opSignatureBuilder.getReturnTypeStyler():this.opSignatureBuilder.getTypeStyler());
            
            // only show multiplicity if not 1 for both min and max
            s.append(multiplicityAsString(p), this.opSignatureBuilder.getSyntaxStyler());
            return s;
        }

        @objid ("dea7f209-3902-406d-a760-b511bf6ba93f")
        private String passingModeAsString(Parameter p) {
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
        private String multiplicityAsString(Parameter param) {
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

        @objid ("73d1a55b-44cf-4ab1-855a-40ff24c20767")
        public  IOParamSignatureBuilder(OpSignatureBuilder opSignatureBuilder) {
            this.opSignatureBuilder = opSignatureBuilder;
        }

    }

}
