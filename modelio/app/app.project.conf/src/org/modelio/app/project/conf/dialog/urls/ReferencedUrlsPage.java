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

package org.modelio.app.project.conf.dialog.urls;

import javax.annotation.PostConstruct;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.modelio.app.project.conf.dialog.IProjectConfPage;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.app.project.conf.plugin.AppProjectConf;

@objid ("1336d7b8-08f8-433a-8033-1f0923867bff")
public class ReferencedUrlsPage implements IProjectConfPage {
    @objid ("dc21b64e-df47-4259-b610-dbabfd147203")
    protected DocumentationSection documentationSection;

    @objid ("3ff14564-fdfa-488c-af30-87e68bd3b43b")
    protected BrowserSection browserSection;

    @objid ("0651eb4e-7f0c-4dab-b307-ef0dbd2d8431")
    private ScrolledForm form;

    /**
     * Creates the SWT controls.
     * <p>
     * Called by E4 injection.
     * 
     * @param parent the parent composite.
     */
    @objid ("67cf1ddf-1c78-4861-ab43-c1734be31e3f")
    @Override
    @PostConstruct
    public ScrolledForm createControls(FormToolkit toolkit, MApplication application, Composite parent) {
        // The form
        this.form = toolkit.createScrolledForm(parent);
        this.form.getBody().setLayout(new TableWrapLayout());
        
        // Documentation Section
        this.documentationSection = new DocumentationSection(this);
        Section dSection = this.documentationSection.createControls(toolkit, this.form.getBody());
        dSection.setLayoutData(new TableWrapData(TableWrapData.FILL));
        
        // Browser Section
        this.browserSection = new BrowserSection();
        final Section bSection = this.browserSection.createControls(toolkit, this.form.getBody());
        bSection.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
        
        // Browser updater
        this.documentationSection.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                ISelection selection = event.getSelection();
                if (selection instanceof IStructuredSelection) {
                    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
                    if (structuredSelection.size() == 1) {
                        Object obj = structuredSelection.getFirstElement();
        
                        if (obj instanceof UrlEntry) {
                            UrlEntry selectedUrl = (UrlEntry) obj;
                            ReferencedUrlsPage.this.browserSection.setInput(selectedUrl);
        
                            // force layout of the new zone
                            boolean expanded = bSection.isExpanded();
                            bSection.setExpanded(!expanded);
                            bSection.setExpanded(expanded);
                        }
                    }
                }
            }
        });
        return this.form;
    }

    @objid ("7bb5b595-20da-43d9-bc5c-b4abc174473a")
    @Override
    public void setInput(ProjectModel displayedProject) {
        this.documentationSection.setInput(displayedProject);
    }

    @objid ("a1b2b8e1-3f99-4e05-9f69-f33fd4103e90")
    @Override
    public Control getControl() {
        return this.form;
    }

    @objid ("309aca47-d9df-473c-bc76-2c06565a4bda")
    @Override
    public String getHelpTopic() {
        return AppProjectConf.I18N.getString("ReferencerlsPage.HelpId");
    }

}
