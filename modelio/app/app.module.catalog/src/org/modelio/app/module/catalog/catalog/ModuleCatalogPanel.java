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

package org.modelio.app.module.catalog.catalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.modelio.app.module.catalog.catalog.update.CatalogUpdatePreferencesPage;
import org.modelio.app.module.catalog.plugin.AppModules;
import org.modelio.gproject.module.IModuleHandle;
import org.modelio.gproject.module.IModuleStore;
import org.modelio.platform.mda.infra.service.CompatibilityHelper.CompatibilityLevel;
import org.modelio.platform.mda.infra.service.CompatibilityHelper;
import org.modelio.platform.preferences.plugin.Preferences;
import org.modelio.platform.ui.panel.IPanelProvider;
import org.modelio.vbasic.version.Version;
import org.modelio.version.ModelioVersion;

/**
 * Panel provider that displays the modules catalog.
 * <p>
 * Can be configured to make an action on double clicking a module.
 */
@objid ("ec2b1457-8850-4ced-832a-f00a45fa18bc")
public class ModuleCatalogPanel implements IPanelProvider {
    @objid ("ddee6091-d8e8-4975-aeca-9c682c5ffa33")
     ModuleCatalogPanelController controller;

    @objid ("5eaa56a1-68f8-45c7-b099-f396707933d0")
     Composite top;

    @objid ("2516ebc7-7c79-4b3d-8f81-2707722db7b0")
     TreeViewer treeViewer;

    @objid ("6f2bb4ee-bb87-43ab-aa10-a53da5638b22")
     Label loading;

    @objid ("26aca1b1-875e-45a3-9989-bbdaaa111d1b")
    private static final Image MODULE_LIST_IMAGE = AppModules.getImageDescriptor("icons/modulelist.png").createImage();

    @objid ("b0764f51-e389-4d21-921d-53bb453bc29c")
    private static final Image MODULE_IMAGE = AppModules.getImageDescriptor("icons/module.png").createImage();

    @objid ("9874257e-cb20-4cfb-a180-1e296f4fa104")
    private static final Image LOADING_IMAGE = AppModules.getImageDescriptor("images/hourglass28x38.png").createImage();

    @objid ("b17fc609-3e5f-4e84-b2a2-c2d3309ab245")
    private IModuleStore catalog;

    @objid ("e44ca7fd-7522-4921-86b4-bc5b1c9e8c0e")
    public ModuleCatalogPanel(IModuleStore catalog) {
        super();
        this.catalog = catalog;
    }

    @objid ("e53a50b6-ab02-4300-b9f6-0b2e2a199ad6")
    @Override
    public Object createPanel(Composite parent) {
        // top level container
        this.top = new Composite(parent, SWT.BORDER);
        this.top.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        this.top.setLayout(new FormLayout());
        
        this.loading = new Label(this.top, SWT.CENTER);
        this.loading.setImage(ModuleCatalogPanel.LOADING_IMAGE);
        
        // List of modules from catalog
        this.treeViewer = new TreeViewer(this.top, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
        Tree tree = this.treeViewer.getTree();
        tree.setHeaderVisible(true);
        this.treeViewer.getTree().setLinesVisible(true);
        
        // The first column displays the module name and version (or the
        // category)
        TreeViewerColumn col1 = new TreeViewerColumn(this.treeViewer, SWT.NONE);
        
        col1.getColumn().setWidth(200);
        col1.getColumn().setText(AppModules.I18N.getString("ModuleCatalogPanel.Module.label"));
        col1.getColumn().setToolTipText(AppModules.I18N.getString("ModuleCatalogPanel.Module.tooltip"));
        col1.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof IModuleHandle) {
                    // display a module
                    IModuleHandle mh = (IModuleHandle) element;
                    return mh.getName() + " " + mh.getVersion().toString("V.R.C");
                } else {
                    // display a category
                    CatalogModulesProvider cp = (CatalogModulesProvider) ModuleCatalogPanel.this.treeViewer.getContentProvider();
                    int totalNb = cp.getAllVersionsOfModule((String) element).size();
                    return element.toString() + " [" + totalNb + "]";
                }
        
            }
        
            @Override
            public Image getImage(Object element) {
                if (element instanceof IModuleHandle) {
                    return ModuleCatalogPanel.MODULE_IMAGE;
                } else {
                    return ModuleCatalogPanel.MODULE_LIST_IMAGE;
                }
            }
        });
        
        // The second column display the minimal version of Modelio that is
        // required to run the module
        TreeViewerColumn col2 = new TreeViewerColumn(this.treeViewer, SWT.CENTER);
        col2.getColumn().setWidth(200);
        col2.getColumn().setText(AppModules.I18N.getString("ModuleCatalogPanel.ModelioRequiredVersion.label"));
        col2.getColumn().setToolTipText(AppModules.I18N.getString("ModuleCatalogPanel.ModelioRequiredVersion.tooltip"));
        col2.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof IModuleHandle) {
                    IModuleHandle mh = (IModuleHandle) element;
                    return mh.getBinaryVersion().toString("V.R.C");
                }
                return "";
            }
        });
        
        // The third column displays the predictable compatibility of the module
        // with the current Modelio version
        TreeViewerColumn col3 = new TreeViewerColumn(this.treeViewer, SWT.NONE);
        col3.getColumn().setWidth(200);
        col3.getColumn().setText(AppModules.I18N.getString("ModuleCatalogPanel.Compatibility.label"));
        col3.getColumn().setToolTipText(AppModules.I18N.getString("ModuleCatalogPanel.Compatibility.tooltip"));
        col3.setLabelProvider(new ColumnLabelProvider() {
            @Override
            public String getText(Object element) {
                if (element instanceof IModuleHandle) {
                    IModuleHandle mh = (IModuleHandle) element;
                    switch (CompatibilityHelper.getCompatibilityLevel(mh)) {
                    case COMPATIBLE:
                        return AppModules.I18N.getString("ModuleCatalogPanel.message.Compatible");
                    case FULLYCOMPATIBLE:
                        return AppModules.I18N.getString("ModuleCatalogPanel.message.FullyCompatible");
                    case MODELIO_TOO_OLD:
                        return AppModules.I18N.getString("ModuleCatalogPanel.message.ModelioTooOld");
                    case MODULE_TOO_OLD:
                        return AppModules.I18N.getString("ModuleCatalogPanel.message.ModuleTooOld");
                    default:
                        break;
                    }
                }
                return "";
            }
        
            @Override
            public Color getForeground(Object element) {
                if (element instanceof IModuleHandle) {
                    IModuleHandle mh = (IModuleHandle) element;
                    switch (CompatibilityHelper.getCompatibilityLevel(mh)) {
                    case COMPATIBLE:
                        return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
                    case FULLYCOMPATIBLE:
                        return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN);
                    case MODELIO_TOO_OLD:
                    case MODULE_TOO_OLD:
                        return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
                    default:
                        break;
                    }
                }
                return super.getForeground(element);
            }
        });
        
        // Option 'show only latest version'
        final Button latestOnly = new Button(this.top, SWT.CHECK);
        latestOnly.setText(AppModules.I18N.getString("ModuleCatalogPanel.ShowLatestVersions"));
        latestOnly.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ModuleCatalogPanel.this.controller.onShowLatestOnly(latestOnly.getSelection());
            }
        
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                //
            }
        });
        
        // Option 'show only compatible versions'
        final Button compatibleOnly = new Button(this.top, SWT.CHECK);
        compatibleOnly.setText(AppModules.I18N.getString("ModuleCatalogPanel.ShowCompatibleVersions"));
        compatibleOnly.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                ModuleCatalogPanel.this.controller.onShowCompatibleOnly(compatibleOnly.getSelection());
            }
        
            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                //
            }
        });
        
        // Carry out attachments (as the different widgets are attached
        // together, can only set their attachements here, when they all have
        // been created)
        FormData fd = new FormData();
        fd.left = new FormAttachment(0, 0);
        fd.right = new FormAttachment(100, 0);
        fd.bottom = new FormAttachment(100, 0);
        compatibleOnly.setLayoutData(fd);
        
        fd = new FormData();
        fd.left = new FormAttachment(0, 0);
        fd.bottom = new FormAttachment(compatibleOnly, 0, SWT.TOP);
        fd.right = new FormAttachment(100, 0);
        latestOnly.setLayoutData(fd);
        
        fd = new FormData();
        fd.top = new FormAttachment(0, 0);
        fd.left = new FormAttachment(0, 0);
        fd.bottom = new FormAttachment(latestOnly, -4);
        fd.right = new FormAttachment(100, 0);
        tree.setLayoutData(fd);
        this.loading.setLayoutData(fd);
        
        // GUI is available, init the contents
        this.controller = new ModuleCatalogPanelController(this, this.catalog);
        this.controller.init();
        
        // Init checkbox states
        latestOnly.setSelection(this.controller.isShowLatestOnly());
        compatibleOnly.setSelection(this.controller.isShowCompatibleOnly());
        
        this.treeViewer.getTree().setVisible(false);
        return this;
    }

    @objid ("45218eab-da58-41f2-9bd8-a92841497250")
    @Override
    public Object getPanel() {
        return this.top;
    }

    @objid ("02b92101-9b04-4d36-97f9-9db07e677f6a")
    @Override
    public void setInput(Object input) {
        if (input instanceof IModuleStore) {
            this.controller.setInput((IModuleStore) input);
            refresh(true, true);
        }
    }

    @objid ("7b9e08c6-1668-4575-9c13-cfca5667a2a7")
    @Override
    public IModuleStore getInput() {
        return this.controller.getInput();
    }

    @objid ("efb62edc-169f-4543-afdf-92a4a16e89c6")
    public Viewer getViewer() {
        return this.treeViewer;
    }

    @objid ("10969d18-58ee-4cae-9a42-c2b2b3895607")
    void refresh(boolean updateLabels, boolean packColumns) {
        this.treeViewer.refresh(updateLabels);
        this.treeViewer.expandToLevel(3);
        
        if (packColumns) {
            for (TreeColumn tc : this.treeViewer.getTree().getColumns()) {
                tc.pack();
            }
        }
    }

    @objid ("836654f7-dbf0-4443-a99f-be30bb413ea0")
    public boolean isShowLatestOnly() {
        return this.controller.isShowLatestOnly();
    }

    @objid ("9da7101b-a0ce-4bca-94d6-60250b15b8fe")
    public List<IModuleHandle> allModules() {
        return this.controller.allModules();
    }

    @objid ("16e7ef0a-c575-4b5a-9199-3f514061151c")
    public void addDoubleClickListener(IDoubleClickListener listener) {
        this.treeViewer.addDoubleClickListener(listener);
    }

    @objid ("4e6fd132-53f4-4577-869d-7ab0d93f074a")
    @Override
    public boolean isRelevantFor(Object obj) {
        return true;
    }

    @objid ("3860d55b-9f3c-4451-94c7-35bfb6b4e8ce")
    @Override
    public String getHelpTopic() {
        return null;
    }

    @objid ("bdd79fa9-182e-4d7c-aa41-059ac283e259")
    @Override
    public void dispose() {
        // nothing to do
    }

    @objid ("231b1002-7740-4497-a09c-0241aa9d061e")
    private static class ModuleCatalogPanelController {
        @objid ("c4a4466f-f357-4694-893b-365bbe5413ed")
         boolean compatibleOnly;

        @objid ("9177f67b-b387-458a-bbec-5110fc2c4617")
         boolean lastestOnly;

        @objid ("56905826-bb0d-4247-9f2d-bd3f4ecf3510")
         ModuleCatalogPanel dialog;

        @objid ("d0f8aa95-371d-4ad4-b291-ab6a262b278c")
        private IModuleStore catalog;

        @objid ("fa2e94df-f646-4048-8865-d1e0d6f12b18")
        private CatalogModulesProvider contentProvider;

        @objid ("c0c4da98-c93a-4436-a938-6ab6b7cdceea")
        private IPreferenceStore prefs;

        @objid ("2a400064-6431-4df7-bbe3-83b42bc05628")
        public ModuleCatalogPanelController(ModuleCatalogPanel dialog, IModuleStore catalog) {
            this.dialog = dialog;
            this.catalog = catalog;
        }

        @objid ("6ff78e30-b221-4b3a-8001-18be25212587")
        public IModuleStore getInput() {
            return this.catalog;
        }

        @objid ("2c49e20d-d813-42c6-b3d2-24522ed49513")
        public void setInput(IModuleStore input) {
            this.catalog = input;
            this.dialog.treeViewer.setInput(this.catalog);
            this.dialog.refresh(true, true);
        }

        @objid ("b127bf2b-afed-4911-a4f4-67592d1a2bf4")
        public void init() {
            this.prefs = Preferences.getPreferences();
            
            this.compatibleOnly = this.prefs.getBoolean(CatalogUpdatePreferencesPage.CATALOG_SHOW_COMPATIBLE);
            this.lastestOnly = this.prefs.getBoolean(CatalogUpdatePreferencesPage.CATALOG_SHOW_LATEST);
            
            this.contentProvider = new CatalogModulesProvider(this.dialog, ModelioVersion.VERSION);
            this.dialog.treeViewer.setContentProvider(this.contentProvider);
            this.dialog.treeViewer.setInput(this.catalog);
            
            this.dialog.refresh(true, true);
        }

        @objid ("398a0633-5204-448d-906e-21b5ec0987f2")
        public void onShowCompatibleOnly(boolean onOff) {
            this.prefs.setValue(CatalogUpdatePreferencesPage.CATALOG_SHOW_COMPATIBLE, onOff);
            this.compatibleOnly = onOff;
            this.contentProvider.refreshModules();
            this.dialog.refresh(true, true);
        }

        @objid ("f9d43c33-aa63-498b-9a01-b73308a4322c")
        public void onShowLatestOnly(boolean onOff) {
            this.prefs.setValue(CatalogUpdatePreferencesPage.CATALOG_SHOW_LATEST, onOff);
            this.lastestOnly = onOff;
            this.dialog.refresh(true, true);
        }

        @objid ("92fd0ab2-5ed2-40a3-a347-9485099f40db")
        public boolean isShowLatestOnly() {
            return this.lastestOnly;
        }

        @objid ("7d89c530-dde9-495b-8d12-c016ec69bedc")
        public boolean isShowCompatibleOnly() {
            return this.compatibleOnly;
        }

        @objid ("727aeebb-bdde-4686-9c61-2b0458dd91a1")
        public List<IModuleHandle> allModules() {
            return this.contentProvider.allModules;
        }

    }

    /**
     * The module content provider is configured by two booleans:
     * <ul>
     * <li>compatibleOnly => list only modules that are compatible</li>
     * <li>latestOnly => list only the latest version of each module</li>
     * </ul>
     * When NOT in 'latestOnly' mode, all the available versions of the modules
     * are listed and organized by categories, a category lists all the versions
     * of a given module (ie there is one category per module type). In
     * 'latestOnly' mode the datamodel does not define categories as there is
     * only one version for each module.
     * 
     * @author phv
     */
    @objid ("6e22d92e-589f-4b9d-8e21-c4189d9a91da")
    private static class CatalogModulesProvider implements ITreeContentProvider {
        @objid ("00985aae-7abe-4c61-b9f5-affa8791434b")
         List<IModuleHandle> allModules = new ArrayList<>();

        @objid ("02a749c5-5f20-454f-88e7-2468accf0a47")
        private Version modelioVersion;

        @objid ("2cfab431-8ea4-46b0-a974-0913b57ff2c1")
         ModuleCatalogPanel panel;

        @objid ("101e6f84-3307-46b9-bac2-0cc64bb3f500")
         TreeMap<String, List<IModuleHandle>> modules = new TreeMap<>();

        @objid ("3eeb6ede-175d-476f-97f2-13f986d45b1a")
        public CatalogModulesProvider(ModuleCatalogPanel panel, Version modelioVersion) {
            this.panel = panel;
            this.modelioVersion = modelioVersion;
        }

        @objid ("2057f28a-700b-4e17-aa86-8497379c2cae")
        public List<IModuleHandle> getAllVersionsOfModule(String name) {
            if (this.modules.containsKey(name)) {
                return this.modules.get(name);
            } else {
                return new ArrayList<>();
            }
        }

        @objid ("0472384f-6821-419e-8b28-9e1954cfc6cd")
        @Override
        public void dispose() {
            // Nothing to dispose
        }

        @objid ("ec24d068-99c8-4629-9603-42e8ed23f5a6")
        @Override
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
            if (newInput == null) {
                this.allModules = new ArrayList<>();
                this.modules = computeModules();
                this.panel.refresh(true, true);
                return;
            }
            
            if (newInput instanceof IModuleStore) {
                final IModuleStore catalog = (IModuleStore) newInput;
                Thread loadingThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            CatalogModulesProvider.this.allModules = catalog.findAllModules(null);
                        } catch (IOException e) {
                            AppModules.LOG.error(e);
                            CatalogModulesProvider.this.allModules = new ArrayList<>();
                        }
                        CatalogModulesProvider.this.modules = computeModules();
            
                        Display.getDefault().asyncExec(new Runnable() {
                            @Override
                            public void run() {
                                if (!CatalogModulesProvider.this.panel.top.isDisposed()) {
                                    CatalogModulesProvider.this.panel.treeViewer.getTree().setVisible(true);
                                    CatalogModulesProvider.this.panel.loading.setVisible(false);
                                    CatalogModulesProvider.this.panel.refresh(true, true);
                                }
                            }
                        });
                    }
                });
                loadingThread.setPriority(Thread.MAX_PRIORITY);
                loadingThread.start();
            }
        }

        @objid ("413a2bd2-83c3-44e8-93e9-b86cc031cc2e")
        TreeMap<String, List<IModuleHandle>> computeModules() {
            TreeMap<String, List<IModuleHandle>> results = new TreeMap<>();
            
            for (IModuleHandle mh : this.allModules) {
                if (mh == null) {
                    continue;
                }
                CompatibilityLevel level = CompatibilityHelper.getCompatibilityLevel(this.modelioVersion, mh.getBinaryVersion());
                if (this.panel.controller.compatibleOnly && !CompatibilityHelper.isCompatible(level)) {
                    // Skip it
                    continue;
                }
            
                List<IModuleHandle> entries = results.get(mh.getName());
                if (entries == null) {
                    entries = new ArrayList<>();
                    results.put(mh.getName(), entries);
                }
                entries.add(mh);
            }
            
            // Sort by descending version
            for (List<IModuleHandle> entries : results.values()) {
                Collections.sort(entries, new ModuleComparator());
            }
            return results;
        }

        @objid ("bdf2c8eb-5232-4d8b-8a3f-62c673ef2f43")
        @Override
        public Object[] getElements(Object inputElement) {
            if (this.panel.controller.lastestOnly) {
                // return the latest versions of the modules
                ArrayList<IModuleHandle> latestVersions = new ArrayList<>();
                for (String key : this.modules.keySet()) {
                    latestVersions.add(this.modules.get(key).get(0));
                }
                return latestVersions.toArray();
            } else {
                // return categories => the keys
                return this.modules.keySet().toArray();
            }
        }

        @objid ("df580f3b-1ac0-47d3-b562-169a4501763e")
        @Override
        public Object[] getChildren(Object parentElement) {
            List<IModuleHandle> versions = this.modules.get(parentElement);
            if (!this.panel.controller.lastestOnly && (versions != null)) {
                return versions.toArray();
            }
            return null;
        }

        @objid ("b90c2fe6-29b8-4b9d-b5ea-82e9e8e16f3d")
        @Override
        public Object getParent(Object element) {
            return null;
        }

        @objid ("30d6a036-57a7-4e4a-a281-86018bb0fb36")
        @Override
        public boolean hasChildren(Object element) {
            if ((element instanceof String) && (getChildren(element) != null)) {
                Object[] childrens = getChildren(element);
                if (childrens != null) {
                    return childrens.length > 0;
                }
            }
            return false;
        }

        @objid ("9e0d2eb6-a620-4002-b94d-1ec5fff986f7")
        public void refreshModules() {
            this.modules = computeModules();
        }

    }

    @objid ("a3492460-7913-403e-8513-0e4de433f269")
    private static class ModuleComparator implements Comparator<IModuleHandle> {
        @objid ("fc87f91e-6bb2-4c93-99bd-68250dbbeeb1")
        @Override
        public int compare(IModuleHandle o1, IModuleHandle o2) {
            if (o1.getVersion().isNewerThan(o2.getVersion())) {
                return -1;
            } else if (o1.getVersion().isOlderThan(o2.getVersion())) {
                return 1;
            } else {
                return 0;
            }
        }

        @objid ("854c7c0e-45d3-4f2b-b468-7cb9a2c78aac")
        public ModuleComparator() {
            // Empty constructor
        }

    }

}
