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

package org.modelio.vcore.utils.metamodel.experts.links;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.utils.metamodel.experts.ILinkExpertHelper;

/**
 * {@link ILinkExpert} that wraps another one.
 * <p>
 * Useful to delegate part of the work to another expert.
 * 
 * @author cmarin
 * @since toutatis
 */
@objid ("eeee14e2-415f-4a92-95f2-4e3369fcdaa0")
public class WrappedLinkExpertHelper implements ILinkExpertHelper {
    @objid ("2849f854-d217-424a-af65-1a71256f6ce3")
    protected final ILinkExpertHelper defaultExpert;

    /**
     * @param defaultExpert the expert to delegate to by default.
     */
    @objid ("c11da907-9fec-4cec-9ff2-098f939740cc")
    public WrappedLinkExpertHelper(ILinkExpertHelper defaultExpert) {
        this.defaultExpert = defaultExpert;
    }

    @objid ("a7d0a448-a102-4c17-8d5b-0da4ad32c216")
    @Override
    public boolean canLink(MClass linkMetaclass, MClass from, MClass to) {
        return this.defaultExpert.canLink(linkMetaclass, from, to);
    }

    @objid ("520cb03c-35d0-4983-b52f-16ef095bc181")
    @Override
    public boolean canLink(MClass linkMetaclass, MObject from, MObject to) {
        return this.defaultExpert.canLink(linkMetaclass, from, to);
    }

    @objid ("99b190dd-b45f-4bad-8043-b377c6d47eda")
    @Override
    public boolean canSource(MClass linkMetaclass, MClass from) {
        return this.defaultExpert.canSource(linkMetaclass, from);
    }

    @objid ("a77da34a-4fef-497c-b737-b38a6659ae3c")
    @Override
    public boolean canSource(MObject linkElement, MObject from) {
        return this.defaultExpert.canSource(linkElement, from);
    }

    @objid ("70920b9d-79f8-4826-9427-cc3e49353e90")
    @Override
    public boolean canTarget(MClass linkMetaclass, MClass to) {
        return this.defaultExpert.canTarget(linkMetaclass, to);
    }

    @objid ("e9cf2641-bf99-4d89-8bf6-334817c2da2f")
    @Override
    public boolean canTarget(MObject linkElement, MObject to) {
        return this.defaultExpert.canTarget(linkElement, to);
    }

    @objid ("2c16f5e0-f0c3-4c76-9794-85b7a306a16e")
    @Override
    public MObject getSource(MObject aLink) {
        return this.defaultExpert.getSource(aLink);
    }

    @objid ("b5081844-552d-4e79-aa25-bd0b5abd3bc5")
    @Override
    public MObject getTarget(MObject aLink) {
        return this.defaultExpert.getTarget(aLink);
    }

    @objid ("4688c969-75c0-4230-bfbb-4f89f8c75b37")
    @Override
    public void setSource(MObject link, MObject oldSource, MObject newSource) throws IllegalArgumentException {
        this.defaultExpert.setSource(link, oldSource, newSource);
    }

    @objid ("f5db39bc-683a-4a40-b334-1462a977b94c")
    @Override
    public void setTarget(MObject link, MObject oldTarget, MObject newTarget) throws IllegalArgumentException {
        this.defaultExpert.setTarget(link, oldTarget, newTarget);
    }

}
