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

package org.modelio.linkeditor.ext;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.linkeditor.panel.ILinkEditorConfiguration;
import org.modelio.linkeditor.panel.ILinkEditorFilter;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.vcore.smkernel.mapi.MClass;

@objid ("c4c5fc37-d9c3-4284-8cdb-dec7cdabcead")
public class LinkEditorConfigurationParameters implements ILinkEditorConfiguration {
    @objid ("71d5c9f4-6091-467f-8964-eb151f60ffa2")
    private int leftDepth = 1;

    @objid ("146de6ba-d78f-4294-b6cd-5a355ce8d0c6")
    private int rightDepth = 1;

    @objid ("e7c15e86-a260-4159-a771-cbf08c839985")
    private Orientation layoutOrientation = Orientation.Horizontal;

    @objid ("acf08f3a-1ffd-4595-9db8-625d9a42bfc2")
    private static LinkEditorConfigurationParameters instance = new LinkEditorConfigurationParameters();

    @objid ("f1563a24-2603-44f2-859a-8090c5eeca00")
    private ParametrizedLinkFilter filter = new ParametrizedLinkFilter();

    @objid ("dfe0f582-56da-413a-a559-ab465b04daba")
    @Override
    public int getLeftDepth() {
        return this.leftDepth;
    }

    @objid ("aca5bbf8-b5f3-4fce-8306-117bfc7baf52")
    public void setLeftDepth(int leftDepth) {
        this.leftDepth = leftDepth;
    }

    @objid ("44f2e6a5-4b8e-4d1a-af7b-475eff1789bf")
    @Override
    public int getRightDepth() {
        return this.rightDepth;
    }

    @objid ("d4565bfc-fc6c-4c54-a23c-07f6e2290e65")
    public void setRightDepth(int rightDeph) {
        this.rightDepth = rightDeph;
    }

    @objid ("72fe25e2-79bb-4b5c-8284-0329ca93e34b")
    @Override
    public Orientation getLayoutOrientation() {
        return this.layoutOrientation;
    }

    @objid ("3d772c08-9738-4b13-a3a8-2d91747d9842")
    public void setLayoutOrientation(Orientation layoutOrientation) {
        this.layoutOrientation = layoutOrientation;
    }

    @objid ("76d43482-05d6-412f-b77c-ad0ae4c97d61")
    public boolean isShowAssociations() {
        return this.filter.showAssociations;
    }

    @objid ("d0699d39-b614-49ba-be89-c5fc9c3e18b9")
    public void setShowAssociations(boolean showAssociations) {
        this.filter.showAssociations = showAssociations;
    }

    @objid ("542644b5-05e3-4be4-815a-7bb7b93eed91")
    public boolean isShowDeps() {
        return this.filter.showDeps;
    }

    @objid ("2b8602af-df14-442b-adc4-1e3f0f701820")
    public void setShowDeps(boolean showDeps) {
        this.filter.showDeps = showDeps;
    }

    @objid ("851ad987-ca53-4449-bf13-4a9c5c97636d")
    public boolean isShowFiltereDeps() {
        return this.filter.showFiltereDeps;
    }

    @objid ("7e163d77-850a-47d4-a95e-2183c47a2e6b")
    public void setShowFiltereDeps(boolean showFiltereDeps) {
        this.filter.showFiltereDeps = showFiltereDeps;
    }

    @objid ("4b159fdb-5609-44c3-ab6c-133208fa81ad")
    public boolean isShowTraces() {
        return this.filter.showTraces;
    }

    @objid ("f1b029be-f7c2-45f5-b64d-1d614902cac9")
    public void setShowTraces(boolean showTraces) {
        this.filter.showTraces = showTraces;
    }

    @objid ("e72842fe-a9b5-4d2d-b699-9c57fe7c0274")
    public boolean isShowInheritance() {
        return this.filter.showInheritance;
    }

    @objid ("f52561ef-8fee-430d-9d9a-5f2257ab6304")
    public void setShowInheritance(boolean showInheritance) {
        this.filter.showInheritance = showInheritance;
    }

    @objid ("6e3a3d06-fef5-4584-8a02-231ebeea2d6d")
    @Override
    public ILinkEditorFilter getLinkFilter() {
        return this.filter;
    }

    @objid ("41432ea6-717d-4ab6-94ad-4b5938c78d8c")
    public static LinkEditorConfigurationParameters getInstance() {
        return instance;
    }

    @objid ("6a6ba35d-b3c2-44fb-aa2a-d7c4dff7b996")
    public boolean isStereotypeEnabled(Stereotype st) {
        return this.filter.enabledStereotypes.contains(st);
    }

    @objid ("a998e45f-b7e8-442d-a69a-b09a1f340d22")
    public List<Stereotype> getEnabledStereotypes() {
        return this.filter.enabledStereotypes;
    }

    @objid ("7b314da1-6b3c-488f-aa71-59629cca495e")
    public void setEnabledStereotypes(List<Stereotype> enabledStereotypes) {
        this.filter.enabledStereotypes = enabledStereotypes;
    }

    @objid ("6d08a923-ea3c-4237-ae07-9c60bd1f4d7b")
    private static class ParametrizedLinkFilter implements ILinkEditorFilter {
        @objid ("110a68cd-25f1-488b-8e18-b95ced258113")
         boolean showAssociations = false;

        @objid ("72fe4408-57a7-4a02-9bae-1a5be5c9f7d2")
         boolean showDeps = false;

        @objid ("ae267d7e-ac8c-4b89-93f3-b25e20b19ccb")
         boolean showFiltereDeps = false;

        @objid ("0a830570-4b01-4d00-94f6-748de7762abf")
         boolean showTraces = false;

        @objid ("27b4b21d-e218-4f5a-baf1-c011b7d25b63")
         boolean showInheritance = false;

        @objid ("974a83c6-916b-47dc-a6b8-b484ff0798cc")
         List<Stereotype> enabledStereotypes = new ArrayList<>();

        @objid ("84f69a2c-a620-408e-95ef-6d84991ff620")
        public ParametrizedLinkFilter() {
        }

        /**
         * Check the stereotype for a given metaclass
         */
        @objid ("b1fea1fc-a10b-49c3-99f2-8f2644188ec6")
        @Override
        public boolean accept(MClass mc, Stereotype st) {
            // Accept any assoc whatever the stereotype
            if (this.showAssociations && AssociationEnd.class.isAssignableFrom(mc.getJavaInterface()))
                return true;
            
            // Accept inheritance linkw whatever the stereoype
            if (this.showInheritance && (Generalization.class.isAssignableFrom(mc.getJavaInterface()) || InterfaceRealization.class.isAssignableFrom(mc.getJavaInterface())))
                return true;
            
            
            if (Dependency.class.isAssignableFrom(mc.getJavaInterface())) {
                return (this.showDeps && st==null)                                                        // Accept pure Dependency links ie no stereotype at all
                        || (this.showTraces && (st != null) && (st.getName().equals("trace")))            // Accept trace links which are Dependency stereotyped <<trace>>
                        || (this.showFiltereDeps && (st != null) && this.enabledStereotypes.contains(st));    // Accept stereotyped Dependency links where the stereotypes is enabled by the filter
            }
            return false;
        }

        /**
         * Check metaclass whatever the stereotypes
         */
        @objid ("b7a05f7e-7591-4d02-adc2-27c4eebb8ae4")
        @Override
        public boolean isLinkTypeEnabled(MClass mc) {
            return (this.showAssociations && AssociationEnd.class.isAssignableFrom(mc.getJavaInterface()))
                                                                                || ((this.showDeps || this.showFiltereDeps || this.showTraces) && Dependency.class.isAssignableFrom(mc.getJavaInterface()))
                                                                                || (this.showInheritance && (Generalization.class.isAssignableFrom(mc.getJavaInterface()) || InterfaceRealization.class.isAssignableFrom(mc.getJavaInterface())));
        }

    }

}
