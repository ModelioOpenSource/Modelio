/* 
 * Copyright 2013-2019 Modeliosoft
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

import javax.annotation.PreDestroy;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.modelio.app.project.conf.dialog.ProjectModel;
import org.modelio.app.project.conf.plugin.AppProjectConf;

@objid ("66dabbaa-ebf8-4146-a923-c8a70d5438da")
public class BrowserSection {
    /**
     * The project that is currently being displayed by the section.
     */
    @objid ("bf5a2602-c8a0-467a-9b71-e25933c34633")
    protected ProjectModel displayedProject;

    @objid ("cd495098-3231-408b-b204-364d9bbdcea8")
    protected IEclipseContext applicationContext;

    @objid ("813b2169-8444-4c25-8532-5f0404089ce9")
    private Browser browser;

    @objid ("baab6e6a-29d6-4f88-8579-73313ea3b635")
    public BrowserSection() {
    }

    /**
     * Update() is called by the referenced url view when the project to be
     * displayed changes or need contents refresh
     * @param selectedProject the project selected in the workspace tree view
     */
    @objid ("5928cf77-f952-4b40-852e-172f9f5ec072")
    public void setInput(UrlEntry urlEntry) {
        if (urlEntry==null) {
            this.browser.setText("");
        } else {            
            this.browser.setUrl(urlEntry.url);
        }
    }

    @objid ("1a6f9bde-4f52-434f-bc0f-691a0c964cbe")
    public Section createControls(final FormToolkit toolkit, final Composite parent) {
        Section section = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE
                | Section.DESCRIPTION);
        section.setText(AppProjectConf.I18N.getString("BrowserSection.SectionText"));
        section.setExpanded(true);
        
        Composite composite = toolkit.createComposite(section, SWT.WRAP);
               
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.marginWidth = 2;
        layout.marginHeight = 2;
        composite.setLayout(layout);
        
        this.browser = createBrowser(composite);
        GridData layoutData = new GridData(SWT.FILL,SWT.FILL,true, true);
        layoutData.heightHint = 400;    //FIXME
        layoutData.minimumHeight = 200;
        this.browser.setLayoutData(layoutData);
        
        // Do it at last
        toolkit.paintBordersFor(composite);
        section.setClient(composite);
        return section;
    }

    @objid ("ad2400df-9fb0-4994-9ff5-0f3d7030cbc9")
    private Browser createBrowser(final Composite parent) {
        Browser aBrowser = new Browser(parent, SWT.NONE);
        aBrowser.setText("");
        return aBrowser;
    }

    @objid ("aa775c67-8a1a-44ce-9b85-4accea2ea9b8")
    @PreDestroy
    private void dispose() {
        this.browser.dispose();
        this.browser = null;
    }

    @objid ("899ca510-4a25-4bef-9eaa-e3da0c42c1b7")
    @Focus
    public void setFocus() {
        this.browser.setFocus();
    }

}
