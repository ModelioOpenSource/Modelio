/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.ui.contributor;

import java.net.URL;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.modelio.api.module.contributor.IWizardContributor;
import org.modelio.api.plugin.Api;
import org.modelio.ui.UIColor;
import org.modelio.ui.panel.IPanelProvider;
import org.osgi.framework.Bundle;

/**
 * Default panel provider working with a {@link IWizardContributor}.
 * <p>
 * Displays a preview image provided by {@link IWizardContributor#getPreviewImage()} .
 * and a link to the help given by {@link IWizardContributor#getHelpUrl()}.
 */
@objid ("3001ac9b-ff21-43b4-96bc-cf565685c447")
public class DefaultWizardPreviewPanel implements IPanelProvider {
    @objid ("26f102ef-13e7-4ccf-90f1-967182698f09")
    private WizardPreviewPanelController controller;

    /**
     * Default constructor.
     */
    @objid ("ae928eff-f2ec-4312-9d18-342a61540ad9")
    public DefaultWizardPreviewPanel() {
        this.controller = new WizardPreviewPanelController();
    }

    @objid ("46cdc75b-709e-470d-8e6b-8ada7cf04aae")
    @Override
    public boolean isRelevantFor(Object obj) {
        return false;
    }

    @objid ("5ee56e2b-8396-4ed9-8240-134a681bbb31")
    @Override
    public Control createPanel(Composite parent) {
        return this.controller.createUi(parent);
    }

    @objid ("3922fa7c-57cc-4f06-9907-226f55c72e83")
    @Override
    public Control getPanel() {
        return this.controller.getUi();
    }

    @objid ("593546bd-cd23-48e5-9993-51db5ed60f74")
    @Override
    public IWizardContributor getInput() {
        return this.controller.getData();
    }

    @objid ("285cc397-fd10-44a6-bd5d-099db5dfff1e")
    @Override
    public void setInput(Object input) {
        if (input instanceof IWizardContributor) {
            this.controller.setData(input);
        } else {
            this.controller.setData(null);
        }
    }

    @objid ("268d3210-0745-4c6b-bb04-85bb580d8ca5")
    @Override
    public void dispose() {
        this.controller.dispose();
    }

    @objid ("4883ae34-d0c4-4071-9e92-a9ba068b8e43")
    @Override
    public String getHelpTopic() {
        return null;
    }

    @objid ("e310d1a4-ba9b-46de-96c3-0d68c9e2dd36")
    private static class WizardPreviewPanelUI {
        @objid ("4f64773b-1f53-4c28-a4d7-76bf32889e5c")
        private static final int PREVIEW_HEIGHT = 300;

        @objid ("745f88bf-e004-4faf-96a0-6b7a4b7cee11")
        private static final int PREVIEW_WIDTH = 400;

        @objid ("42584e91-bc57-4fa0-bf83-9f01f5b7ade0")
        private WizardPreviewPanelController controller;

        @objid ("28ab14d3-a7e8-4f3d-adfd-ea97ca51e67d")
        private Group previewGroup = null;

        @objid ("375aa191-eff3-40e9-838f-049ef9e24818")
        private Label previewImage;

        @objid ("9347ecdd-999f-4a51-a310-c88609f2696b")
        private StyledText detailsText;

        @objid ("b327dd25-c119-48cb-8fc8-8f1bc29531c2")
        private Link detailsLink;

        @objid ("b16d844a-6e18-4bc0-834b-1e2e8bc53c14")
        public WizardPreviewPanelUI(WizardPreviewPanelController controller) {
            this.controller = controller;
        }

        @objid ("752bb2db-6374-44b8-a8eb-a6d1c3f48fed")
        public Control createUI(Composite parent) {
            this.previewGroup = new Group(parent, SWT.BORDER_SOLID);
            this.previewGroup.setText(Api.I18N.getMessage("Ui.CreationWizard.PreviewGroup.label"));
            this.previewGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
            this.previewGroup.setLayout(new GridLayout(1, false));
            
            this.previewImage = new Label(this.previewGroup, SWT.BORDER);
            this.previewImage.setSize(PREVIEW_WIDTH, PREVIEW_HEIGHT);
            GridData gd = new GridData();
            gd.heightHint = PREVIEW_HEIGHT;
            gd.widthHint = PREVIEW_WIDTH;
            gd.horizontalAlignment = SWT.CENTER;
            this.previewImage.setLayoutData(gd);
            
            this.detailsText = new StyledText(this.previewGroup, SWT.MULTI | SWT.WRAP);
            this.detailsText.setForeground(UIColor.LABEL_TIP_FG);
            this.detailsText.setEditable(false);
            this.detailsText.setBackground(this.previewGroup.getBackground());
            
            GridData gd2 = new GridData(SWT.FILL, SWT.FILL, true, true);
            this.detailsText.setLayoutData(gd2);
            
            this.detailsLink = new Link(this.previewGroup, SWT.NONE);
            GridData gd3 = new GridData(SWT.FILL, SWT.FILL, true, false);
            this.detailsLink.setLayoutData(gd3);
            
            // Install listeners
            this.detailsLink.addSelectionListener(new SelectionAdapter() {
                @SuppressWarnings("synthetic-access")
                @Override
                public void widgetSelected(SelectionEvent e) {
                    WizardPreviewPanelUI.this.controller.onDetailsLinkSelected((String) ((Link) e.getSource()).getData("url"));
                }
            });
            return this.previewGroup;
        }

        @objid ("8d9f2743-faae-486b-b4fe-82d4ff732be1")
        public void dispose() {
            if (this.previewImage.getImage() != null) {
                this.previewImage.getImage().dispose();
                this.previewImage.setImage(null);
            }
            this.previewGroup.dispose();
        }

        @objid ("bc6c951f-483f-4eec-9cd5-f01d0f010489")
        private void switchPreviewImage(ImageDescriptor imageDescriptor) {
            // Dispose existing preview image
            Image img = this.previewImage.getImage();
            if (img != null) {
                this.previewImage.setImage(null);
                img.dispose();
            }
            
            // Create and setup the new preview image
            if (imageDescriptor != null) {
                this.previewImage.setImage(imageDescriptor.createImage());
            } else {
                Bundle bundle = Api.getContext().getBundle();
                URL imageUrl = FileLocator.find(bundle, new Path("images/noimagepreview400x300.png"), null);
                this.previewImage.setImage(ImageDescriptor.createFromURL(imageUrl).createImage(true));
            }
        }

        @objid ("c41a81f3-0baa-4a27-955d-895de9209d6d")
        public void update(IWizardContributor selectedContributor) {
            // Update the preview text and image
            if (selectedContributor != null) {
                // Preview text
                this.detailsText.setText(selectedContributor.getDetails());
                // Preview image
                switchPreviewImage(selectedContributor.getPreviewImage());
                // Details link
                String helpUrl = selectedContributor.getHelpUrl();
                this.detailsLink.setText((helpUrl != null) ? Api.I18N.getMessage("Ui.CreationWizard.More")
                        : "");
                this.detailsLink.setData("url", helpUrl);
            } else {
                this.detailsText.setText("");
                switchPreviewImage(null);
                this.detailsLink.setText("");
                this.detailsLink.setData("url", null);
            }
        }

    }

    @objid ("2b476a6e-8d88-400b-bde0-927352a61b49")
    private static class WizardPreviewPanelController {
        @objid ("1db154f3-1251-4253-a3f4-60b3b78dae07")
        private IWizardContributor data;

        @objid ("36298144-da7f-4a2e-bb9b-c645ef111256")
        private WizardPreviewPanelUI ui;

        @objid ("aae30daa-6a27-4d7c-99e3-a2d8596f82e8")
        public IWizardContributor getData() {
            return this.data;
        }

        @objid ("b055ead5-57ed-4d58-bf4c-9579c9e78982")
        public void setData(Object data) {
            this.data = (IWizardContributor) data;
            if (this.ui != null) {
                this.ui.update(this.data);
            }
        }

        @objid ("c95362fd-17f4-472e-b35b-fba1f6b73e2f")
        public Control createUi(Composite parent) {
            this.ui = new WizardPreviewPanelUI(this);
            Control control = this.ui.createUI(parent);
            this.ui.update(this.data);
            return control;
        }

        @objid ("8ca14a2a-e3f0-42ff-8b98-472a4a30e1a2")
        @SuppressWarnings("synthetic-access")
        public Control getUi() {
            return this.ui.previewGroup;
        }

        @objid ("b3bdfb60-d142-427f-b2e0-aa22420df6e9")
        public void dispose() {
            this.ui.dispose();
            this.ui = null;
        }

        @objid ("f302f7aa-f840-447e-a03f-2764426d4612")
        public void onDetailsLinkSelected(String helpUrl) {
            if (helpUrl != null) {
                BrowserDialog dialog = new BrowserDialog(getUi().getShell(), helpUrl);
                dialog.open();
            }
        }

        @objid ("507bcd09-60f1-41ba-85c1-0163c2b5a1d4")
        public WizardPreviewPanelController() {
            super();
        }

    }

}
