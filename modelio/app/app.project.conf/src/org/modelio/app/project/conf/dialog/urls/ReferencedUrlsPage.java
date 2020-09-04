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

package org.modelio.app.project.conf.dialog.urls;

import javax.annotation.PostConstruct;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.modelio.app.project.conf.dialog.ProjectModel;

@objid ("1336d7b8-08f8-433a-8033-1f0923867bff")
public class ReferencedUrlsPage {
    @objid ("dc21b64e-df47-4259-b610-dbabfd147203")
    protected DocumentationSection documentationSection;

    @objid ("3ff14564-fdfa-488c-af30-87e68bd3b43b")
    protected BrowserSection browserSection;

    /**
     * Creates the SWT controls.
     * <p>
     * Called by E4 injection.
     * @param parent the parent composite.
     */
    @objid ("67cf1ddf-1c78-4861-ab43-c1734be31e3f")
    @PostConstruct
    public ScrolledForm createControls(FormToolkit toolkit, final Composite parent) {
        // The form
        ScrolledForm form = toolkit.createScrolledForm(parent);
        form.getBody().setLayout(new TableWrapLayout());
        
        // Documentation Section
        this.documentationSection = new DocumentationSection(this);
        Section dSection = this.documentationSection.createControls(toolkit, form.getBody());
        dSection.setLayoutData(new TableWrapData(TableWrapData.FILL));
        
        // Browser Section
        this.browserSection = new BrowserSection();
        final Section bSection = this.browserSection.createControls(toolkit, form.getBody());
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
        return form;
    }

    @objid ("7bb5b595-20da-43d9-bc5c-b4abc174473a")
    public void setInput(ProjectModel displayedProject) {
        this.documentationSection.setInput(displayedProject);
    }

}
