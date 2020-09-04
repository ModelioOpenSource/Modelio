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

package org.modelio.linkeditor.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;

@objid ("a831601d-f401-4ee5-8be0-ab85f8fe061a")
public class LinkEditorConfiguration implements ILinkEditorConfiguration {
    @objid ("29ed9b77-1e7e-4a27-8f34-51d8a8671851")
    private Orientation layoutOrientation = Orientation.Horizontal;

    @objid ("7cbc5f12-bcee-478a-a55c-4a30d4697049")
    private int leftDepth = 1;

    @objid ("cd067b15-223f-4247-8173-51fbd5000a5f")
    private int rightDepth = 1;

    @objid ("eeca2864-d9b9-4e3e-967e-4c11464d3364")
    private static final ILinkEditorFilter ANY_LINK = new ILinkEditorFilter() {
		@Override
		public boolean accept(MClass enabledLinkType, Stereotype st) {
			return true;
		}
		@Override
		public boolean isLinkTypeEnabled(MClass mc) {
			return true;
		}
	};

    @objid ("1c26e947-b281-4c6a-8256-b1b420ba6138")
    private ILinkEditorFilter linkFilter = LinkEditorConfiguration.ANY_LINK;

    /**
     * Full featured C'Tor
     * @param id
     * @param leftDepth
     * @param rightDepth
     * @param orientation
     * @param linkFilter
     */
    @objid ("cd903e53-756c-4a2e-bfe7-9aa516915656")
    public LinkEditorConfiguration(int leftDepth, int rightDepth, Orientation orientation, ILinkEditorFilter linkFilter) {
        this.leftDepth = leftDepth;
        this.rightDepth = rightDepth;
        this.layoutOrientation = orientation;
        this.linkFilter = (linkFilter != null) ? linkFilter : LinkEditorConfiguration.ANY_LINK;
    }

    /**
     * Default C'tor
     */
    @objid ("0e819fcc-9ace-4eea-88f6-604bc5f682c3")
    LinkEditorConfiguration() {
        this.leftDepth = 1;
        this.rightDepth = 1;
        this.layoutOrientation = Orientation.Horizontal;
        this.linkFilter = LinkEditorConfiguration.ANY_LINK;
    }

    /**
     * Full clone C'tor
     * @param config
     */
    @objid ("c3b9dc8e-b58c-4327-8f11-2d592e99782b")
    LinkEditorConfiguration(ILinkEditorConfiguration config) {
        this(config.getLeftDepth(), config.getRightDepth(), config.getLayoutOrientation(), config.getLinkFilter());
    }

    /**
     * Apply the values from 'aConfig' to this instance.
     * @param aConfig
     */
    @objid ("d83299c4-1226-483a-8009-d48e63ef5d5b")
    public void apply(ILinkEditorConfiguration aConfig) {
        setLeftDepth(aConfig.getLeftDepth());
        setRightDepth(aConfig.getRightDepth());
        setLayoutOrientation(aConfig.getLayoutOrientation());
        setLinkFilter(aConfig.getLinkFilter());
    }

    @objid ("93153d9a-5a0d-446d-848d-0a10d0f07a0a")
    @Override
    public Orientation getLayoutOrientation() {
        // Automatically generated method. Please do not modify this code.
        return this.layoutOrientation;
    }

    @objid ("32836469-4b76-4df6-a51d-d1e4d4123c88")
    @Override
    public int getLeftDepth() {
        // Automatically generated method. Please do not modify this code.
        return this.leftDepth;
    }

    @objid ("b18aa8da-110f-4726-8a6f-b10781e40414")
    @Override
    public ILinkEditorFilter getLinkFilter() {
        return this.linkFilter;
    }

    @objid ("1b571d4f-5c3c-48ad-a8ad-cb1fd3557ee7")
    @Override
    public int getRightDepth() {
        // Automatically generated method. Please do not modify this code.
        return this.rightDepth;
    }

    @objid ("c9417053-616f-48d7-9a13-07a12acb4782")
    public void setLayoutOrientation(Orientation value) {
        // Automatically generated method. Please do not modify this code.
        this.layoutOrientation = value;
    }

    @objid ("af6ed48a-44ec-427f-ae6c-2a86501499a9")
    public void setLeftDepth(int value) {
        // Automatically generated method. Please do not modify this code.
        this.leftDepth = value;
    }

    @objid ("6d72f0cc-1ec2-4474-87cd-0202a9f0ce2f")
    public void setLinkFilter(ILinkEditorFilter linkFilter) {
        this.linkFilter = linkFilter != null ? linkFilter : LinkEditorConfiguration.ANY_LINK;
    }

    @objid ("f33f6ab0-09d1-413c-91d2-63fc2f8ce04a")
    public void setRightDepth(int value) {
        // Automatically generated method. Please do not modify this code.
        this.rightDepth = value;
    }

}
