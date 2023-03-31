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
package org.modelio.app.ui.statusbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.modelio.app.ui.plugin.AppUi;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;

@objid ("8234e97e-8969-452b-8332-2be3ddb43813")
public class AppStatusBar {
    @objid ("5b6da2ed-eae4-48c0-8799-871e2eea4e27")
    private List<GroupDescriptor> leftContributors = new ArrayList<>();

    @objid ("1519df5f-b00c-4828-ad31-977de66ddb50")
    private List<GroupDescriptor> centerContributors = new ArrayList<>();

    @objid ("465ab5b9-93e3-4ada-a91c-664c41c918b8")
    private List<GroupDescriptor> rightContributors = new ArrayList<>();

    @objid ("ca81a141-f791-416d-954d-f19da2d6412f")
    private Composite barContainer;

    @objid ("3b133693-6b04-44b1-861d-5750d89e12ab")
    private Composite leftContainer;

    @objid ("f5cc934a-1b62-4bfa-bb76-b93f720546f2")
    private Composite centerContainer;

    @objid ("667aeacb-402a-4b78-ac4e-5b6c9ba8f817")
    private Composite rightContainer;

    @objid ("45510bbe-c767-407c-a403-b235c217727d")
    @PostConstruct
    public void createControls(Composite parent, final IEclipseContext context) {
        // Collect contributors from extension point
        collectContributors();
        
        // Create the bar container
        // This will span the whole application widht (see 'stretch' tag in e4xmi contribution )
        this.barContainer = new Composite(parent, SWT.NONE);
        GridLayout barLayout = new GridLayout(3, false);
        barLayout.verticalSpacing = 0;
        barLayout.marginHeight = 0;
        
        this.barContainer.setLayout(barLayout);
        // this.barContainer.setBackground(UIColor.BLUE);
        
        // Create the area containers
        this.leftContainer = new Composite(this.barContainer, SWT.NONE);
        this.leftContainer.setLayout(createAreaContainerLayout());
        this.leftContainer.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
        // this.leftContainer.setBackground(UIColor.ORANGE);
        
        this.centerContainer = new Composite(this.barContainer, SWT.NONE);
        this.centerContainer.setLayout(createAreaContainerLayout());
        this.centerContainer.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, false));
        // this.centerContainer.setBackground(UIColor.GREEN);
        
        this.rightContainer = new Composite(this.barContainer, SWT.NONE);
        this.rightContainer.setLayout(createAreaContainerLayout());
        this.rightContainer.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
        // this.rightContainer.setBackground(UIColor.YELLOW);
        
        // Fill area containers
        for (GroupDescriptor group : this.leftContributors) {
            Composite groupControl = createGroupControl(group, this.leftContainer);
            createGroupItemControls(group, groupControl, context);
            // if (group != this.leftContributors.get(this.leftContributors.size()-1)) {
            createGroupSeparator(this.leftContainer);
            // }
        }
        if (this.centerContributors.size() > 0)
            createGroupSeparator(this.centerContainer);
        
        for (GroupDescriptor group : this.centerContributors) {
            Composite groupControl = createGroupControl(group, this.centerContainer);
            createGroupItemControls(group, groupControl, context);
            createGroupSeparator(this.centerContainer);
        }
        for (GroupDescriptor group : this.rightContributors) {
            createGroupSeparator(this.rightContainer);
            Composite groupControl = createGroupControl(group, this.rightContainer);
            createGroupItemControls(group, groupControl, context);
        }
        
    }

    @objid ("d7c26b63-68a7-436e-b7e1-d65dc643c1d8")
    private void createGroupSeparator(Composite parent) {
        // Group Separator
        new Label(parent, SWT.SEPARATOR).setLayoutData(new RowData(-1, 10));
        
    }

    @objid ("3b93cc1b-18c7-4e58-a400-1f30296c3c08")
    private Composite createGroupControl(GroupDescriptor group, Composite parent) {
        // Group composite
        Composite groupContainer = new Composite(parent, SWT.NONE);
        groupContainer.setLayout(createGroupLayout());
        if (group.label != null) {
            new CLabel(groupContainer, SWT.NONE).setText(group.label);
        }
        return groupContainer;
    }

    @objid ("53fd594e-7594-4a02-b88e-3aa66dc673e1")
    private void createGroupItemControls(GroupDescriptor group, Composite parent, IEclipseContext context) {
        for (ItemDescriptor item : group.items) {
            // Inject the contribution
            ContextInjectionFactory.inject(item.contribution, context);
        
            // Get the contribution control
            Control l = item.contribution.createControl(parent);
            int heightHint = 30;
            int widthHint = (item.widthHint != null) ? item.widthHint : -1;
            l.setLayoutData(new RowData(widthHint, heightHint));
            if (item.tooltip != null) {
                l.setToolTipText(item.tooltip);
            }
        }
        
    }

    @objid ("cf494ce4-9571-4d6c-93bd-9f1111421579")
    private RowLayout createAreaContainerLayout() {
        RowLayout rowLayout = new RowLayout();
        rowLayout.wrap = false;
        rowLayout.pack = true;
        rowLayout.justify = false;
        rowLayout.type = SWT.HORIZONTAL;
        rowLayout.spacing = 0;
        rowLayout.fill = true;
        rowLayout.center = true;
        rowLayout.marginTop = 0;
        rowLayout.marginLeft = 2;
        rowLayout.marginRight = 2;
        rowLayout.marginBottom = 0;
        return rowLayout;
    }

    @objid ("5d9435e5-51c9-4df0-8214-8c3fd991cffa")
    private RowLayout createGroupLayout() {
        RowLayout groupLayout = new RowLayout();
        groupLayout.wrap = false;
        groupLayout.pack = true;
        groupLayout.justify = false;
        groupLayout.type = SWT.HORIZONTAL;
        groupLayout.spacing = 1;
        groupLayout.fill = true;
        groupLayout.center = true;
        groupLayout.marginTop = 0;
        groupLayout.marginBottom = 0;
        return groupLayout;
    }

    @objid ("1ce552a9-97ca-4bb5-a6b4-f392ad6195c8")
    @Inject
    @Optional
    public void onUpdate(@UIEventTopic ("org/modelio/app/ui/statusbar") Object obj) {
        if (obj != null) {
            if (obj instanceof List) {
                for (Object o : (List) obj) {
                    Control c = (Control) o;
                    c.requestLayout();
                }
            } else {
                Control c = (Control) obj;
                c.requestLayout();
            }
            this.barContainer.requestLayout();
        }
        return;
    }

    @objid ("33dabece-705a-4827-851e-7022112b46c9")
    private void collectContributors() {
        // collect statically registered contributions
        ExtensionPointContributionManager em = new ExtensionPointContributionManager("org.modelio.app.ui.statusbar");
        
        Collection<IConfigurationElement> groups = em.getExtensions("group");
        for (final IConfigurationElement g : groups) {
            GroupDescriptor group = new GroupDescriptor(g);
            switch (group.area) {
            case left:
                this.leftContributors.add(group);
                break;
            case center:
                this.centerContributors.add(group);
                break;
            case right:
                this.rightContributors.add(group);
                break;
        
            }
        }
        
        // Sort contributions
        this.leftContributors.sort(new Comparator<GroupDescriptor>() {
            @Override
            public int compare(GroupDescriptor g1, GroupDescriptor g2) {
                return Integer.compare(g1.priority, g2.priority);
            }
        });
        this.centerContributors.sort(new Comparator<GroupDescriptor>() {
            @Override
            public int compare(GroupDescriptor g1, GroupDescriptor g2) {
                return Integer.compare(g1.priority, g2.priority);
            }
        });
        this.rightContributors.sort(new Comparator<GroupDescriptor>() {
            @Override
            public int compare(GroupDescriptor g1, GroupDescriptor g2) {
                return -Integer.compare(g1.priority, g2.priority);
            }
        });
        
    }

    @objid ("dab8c4bc-24d4-4ea6-950b-262f959ec063")
    public enum Area {
        @objid ("b6c88c7b-dad2-4a5f-a846-768f980d68c9")
        left,
        @objid ("7471afed-4fa3-44b0-afbe-62778ce100cd")
        center,
        @objid ("03a928d2-8cb0-4a8c-b999-af3c06af3c63")
        right;

    }

    @objid ("bfed8a15-7afc-4852-b8c9-0bc88d14fe3b")
    private static class GroupDescriptor {
        @objid ("9834b0bf-798f-4de6-a1da-a0d36d9f59de")
        public String label = null;

        @objid ("866c0b1d-ce2b-41e8-b89b-8a1c04d74da8")
        public int priority = 0;

        @objid ("d40f7834-b2a6-409f-87a9-ac6f12fe671b")
        public Area area = Area.left;

        @objid ("3ccac8de-0a39-4bad-8a8b-18a4e824bcd7")
        public List<ItemDescriptor> items = new ArrayList<>();

        @objid ("37c8b250-b862-4583-a3ce-87bec022b5db")
        public  GroupDescriptor(IConfigurationElement g) {
            this.label = g.getAttribute("label");
            try {
                this.priority = Integer.parseInt(g.getAttribute("priority"));
            } catch (NumberFormatException e1) {
                this.priority = 0;
                AppUi.LOG.debug(e1);
                AppUi.LOG.warning(String.format("Error in configuration element '%s': bad 'priority' value, using default value (0).", g));
            }
            this.area = Area.valueOf(g.getAttribute("area"));
            parseItems(g);
            
        }

        @objid ("7ef9c14b-63d9-4239-ab1d-741b310615e1")
        private void parseItems(IConfigurationElement g) {
            for (IConfigurationElement e : g.getChildren("item")) {
                ItemDescriptor item = new ItemDescriptor();
                try {
                    item.contribution = (IStatusBarContribution) e.createExecutableExtension("class");
                    item.tooltip = e.getAttribute("tooltip");
                    if (e.getAttribute("widthHint") != null) {
                        try {
                            item.widthHint = Integer.decode(e.getAttribute("widthHint"));
                        } catch (NumberFormatException e2) {
                            item.widthHint = null;
                        }
                    }
                    this.items.add(item);
                } catch (CoreException e1) {
                    // Skip this item, log the exception
                    AppUi.LOG.debug(e1);
                    AppUi.LOG.warning(String.format("Error in configuration element '%s', ignoring contribution.", e));
                }
            }
            
        }

    }

    @objid ("bb815088-219d-44ae-9f4c-d1aa3afcbd95")
    private static class ItemDescriptor {
        @objid ("baffe27b-622e-41e6-ab5e-ac9e0b7d88fe")
        public Integer widthHint = null;

        @objid ("f380760b-1bf2-4e4c-9d4f-32094ec56be4")
        public Integer width = null;

        @objid ("23c6072f-aa99-43b1-a41c-4c231fa3faeb")
        public String tooltip = null;

        @objid ("6c657054-965c-4447-b7ca-2d8fc192690c")
        public IStatusBarContribution contribution = null;

    }

}
