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

/* WARNING: GENERATED FILE -  DO NOT EDIT
     Metamodel: Standard, version 2.3.00, by Modeliosoft
     Generator version: 3.8.00
     Generated on: Sep 7, 2018
*/
package org.modelio.metamodel.impl.uml.statik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.metamodel.impl.uml.infrastructure.UmlModelElementImpl;
import org.modelio.metamodel.impl.uml.statik.LinkEndData;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.metamodel.visitors.IModelVisitor;
import org.modelio.vcore.smkernel.SmConstrainedList;
import org.modelio.vcore.smkernel.SmDepVal;
import org.modelio.vcore.smkernel.SmList;
import org.modelio.vcore.smkernel.SmObjectImpl;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;

@objid ("000ff2da-c4bf-1fd8-97fe-001ec947cd2a")
public class LinkEndImpl extends UmlModelElementImpl implements LinkEnd {
    /**
     * Compute the link navigation way from all roles construction.
     * @return the link navigability.
     */
    @objid ("1efdf05d-4abe-4809-a5e2-6e5cf051d3a6")
    @Override
    public boolean isNavigable() {
        return getTarget() != null;
    }

    @objid ("007a3168-2787-1080-943a-001ec947cd2a")
    @Override
    public void setNavigable(final boolean isNavigable) {
        LinkEnd opposite = getOpposite();
        boolean otherNavigable = opposite.isNavigable();
        
        // The source instance is the current source or the opposite end's target according to the old navigability
        Instance source = getSource() != null ? getSource() : opposite.getTarget();
        
        // The target instance is the opposite end's source or the current target according to the old navigability
        Instance target = opposite.getSource() != null ? opposite.getSource() : getTarget();
        
        if (isNavigable && !otherNavigable) { // THIS SIDE
            this.setSource(source);
            this.setTarget(target);
        
            opposite.setSource(null);
            opposite.setTarget(null);
        } else if (!isNavigable && otherNavigable) { // OTHER SIDE
            this.setSource(null);
            this.setTarget(null);
        
            opposite.setSource(target);
            opposite.setTarget(source);
        } else if (isNavigable && otherNavigable) { // BOTH SIDES
            this.setSource(source);
            this.setTarget(target);
        
            opposite.setSource(target);
            opposite.setTarget(source);
        } else if (!isNavigable && !otherNavigable) { // NONE
            this.setSource(source);
            this.setTarget(null);
        
            opposite.setSource(target);
            opposite.setTarget(null);
        }
    }

    @objid ("0047aacc-27d2-1080-943a-001ec947cd2a")
    @Override
    public void setTarget(final Instance value, boolean fixModel) {
        if (fixModel) {
            boolean isNavigable = isNavigable();
        
            LinkEnd opposite = getOpposite();
            boolean otherNavigable = opposite.isNavigable();
        
            // The source instance is the current source or the opposite end's target according to the old navigability
            Instance source = getSource() != null ? getSource() : opposite.getTarget();
        
            Instance target = value;
        
            if (isNavigable && !otherNavigable) { // THIS SIDE
                this.setSource(source);
                this.setTarget(target);
        
                opposite.setSource(null);
                opposite.setTarget(null);
            } else if (!isNavigable && otherNavigable) { // OTHER SIDE
                this.setSource(source);
                this.setTarget(target);
        
                opposite.setSource(target);
                opposite.setTarget(source);
            } else if (isNavigable && otherNavigable) { // BOTH SIDES
                this.setSource(source);
                this.setTarget(target);
        
                opposite.setSource(target);
                opposite.setTarget(source);
            } else if (!isNavigable && !otherNavigable) { // NONE
                this.setSource(source);
                this.setTarget(target);
        
                opposite.setSource(null);
                opposite.setTarget(null);
            }
        } else {
            setTarget(value);
        }
    }

    @objid ("000aa226-27ef-1080-943a-001ec947cd2a")
    @Override
    public void setSource(final Instance value, boolean fixModel) {
        if (fixModel) {
            boolean isNavigable = isNavigable();
        
            LinkEnd opposite = getOpposite();
            boolean otherNavigable = opposite.isNavigable();
        
            Instance source = value;
        
            // The target instance is the opposite end's source or the current target according to the old navigability
            Instance target = opposite.getSource() != null ? opposite.getSource() : getTarget();
        
            if (isNavigable && !otherNavigable) { // THIS SIDE
                this.setSource(source);
                this.setTarget(target);
        
                opposite.setSource(null);
                opposite.setTarget(null);
            } else if (!isNavigable && otherNavigable) { // OTHER SIDE
                this.setSource(source);
                this.setTarget(target);
        
                opposite.setSource(target);
                opposite.setTarget(source);
            } else if (isNavigable && otherNavigable) { // BOTH SIDES
                this.setSource(source);
                this.setTarget(target);
        
                opposite.setSource(target);
                opposite.setTarget(source);
            } else if (!isNavigable && !otherNavigable) { // NONE
                this.setSource(source);
                this.setTarget(null);
        
                opposite.setSource(target);
                opposite.setTarget(null);
            }
        } else {
            setSource(value);
        }
    }

    @objid ("001f2be2-1a19-10a1-88a0-001ec947cd2a")
    @Override
    public SmObjectImpl getCompositionOwner() {
        Instance src = getSource();
        if (src != null) {
            return (SmObjectImpl) src;
        } else {
            // Return the 'opposite' role only if it has a source:
            // Avoid cycle if both ends have no source.
            // Do not use 'Opposite' role, it is the wrong way and using it has horrible consequences
            // in repository handle initializations.
            LinkEnd opp = getOppositeOwner();
            if (opp != null && opp.getSource() != null) {
                return (SmObjectImpl) opp;
            } else {
                return null;
            }
        }
    }

    @objid ("71066600-5b42-11e2-90ce-002564c97630")
    @Override
    public List<SmObjectImpl> getCompositionChildren() {
        final List<SmObjectImpl> compositionChildren = super.getCompositionChildren();
        
        // Avoid composition loops...
        // Do not use 'OppositeOwner' role, it is the wrong way and using it may have 
        // horrible consequences in repository handle initializations.
        LinkEnd opp = getOpposite();
        if (opp != null && opp.getSource() != null) {
            // The other side has a 'strong' owner, do not return it as a child
            compositionChildren.remove(opp);
        }
        return compositionChildren;
    }

    @objid ("001f4a50-1a19-10a1-88a0-001ec947cd2a")
    @Override
    public SmDepVal getCompositionRelation() {
        SmObjectImpl obj;
        
        // First look for 'source'
        obj = (SmObjectImpl) getDepVal(((LinkEndSmClass) getClassOf()).getSourceDep());
        if (obj != null) {
            return new SmDepVal(((LinkEndSmClass) getClassOf()).getSourceDep(), obj);
        }
        
        // Return the 'opposite' role only if it has a source:
        // Avoid cycle if both ends have no source.
        LinkEnd opp = getOpposite();
        
        if (opp != null && opp.getSource() != null) {
            return new SmDepVal(((LinkEndSmClass) getClassOf()).getOppositeOwnerDep(), opp);
        } else {
            return null;
        }
    }

    @objid ("0aed289b-c84f-4d32-9047-10a14bf76274")
    @Override
    public Instance getOwner() {
        Instance source = getSource();
        if (source != null) {
            return source;
        } else {
            LinkEnd opposite = getOpposite();
            return opposite != null ? opposite.getTarget() : null;
        }
    }

    @objid ("891a32f4-6ac7-42a6-b286-ad0bf75273a5")
    @Override
    public void afterEraseDepVal(SmDependency dep, SmObjectImpl value) {
        super.afterEraseDepVal(dep, value);
        
        if (dep == ((LinkEndSmClass) getClassOf()).getSourceDep()) {
            // Workaround bug where the storage handle is not updated
            LinkEnd opposite = getOpposite();
            if (opposite != null) {
                Instance src = opposite.getSource();
                if (src != null) {
                    // detach and attach again
                    opposite.setOpposite(null);
                    opposite.setSource(null);
                    opposite.setSource(src);
                    opposite.setOpposite(this);
                }
            }
        
        }
    }

    @objid ("9a2854fe-ec52-4f3d-bc7f-c8d8640aff01")
    @Override
    public boolean isIsOrdered() {
        return (Boolean) getAttVal(((LinkEndSmClass)getClassOf()).getIsOrderedAtt());
    }

    @objid ("f5090b96-176e-482b-af01-9011856cfa85")
    @Override
    public void setIsOrdered(boolean value) {
        setAttVal(((LinkEndSmClass)getClassOf()).getIsOrderedAtt(), value);
    }

    @objid ("c8b048e5-6e51-484a-b805-508ecda51a71")
    @Override
    public boolean isIsUnique() {
        return (Boolean) getAttVal(((LinkEndSmClass)getClassOf()).getIsUniqueAtt());
    }

    @objid ("05894516-c6e9-4114-871f-6b35ab818058")
    @Override
    public void setIsUnique(boolean value) {
        setAttVal(((LinkEndSmClass)getClassOf()).getIsUniqueAtt(), value);
    }

    @objid ("02d14b71-65a0-4be9-b12b-0e8d43635642")
    @Override
    public String getMultiplicityMax() {
        return (String) getAttVal(((LinkEndSmClass)getClassOf()).getMultiplicityMaxAtt());
    }

    @objid ("4fd622e8-503b-4676-bfbb-23e2c3f9532d")
    @Override
    public void setMultiplicityMax(String value) {
        setAttVal(((LinkEndSmClass)getClassOf()).getMultiplicityMaxAtt(), value);
    }

    @objid ("d39eaf85-96d4-42b9-af7c-afe139ae3ea2")
    @Override
    public String getMultiplicityMin() {
        return (String) getAttVal(((LinkEndSmClass)getClassOf()).getMultiplicityMinAtt());
    }

    @objid ("e2d3b562-4872-4d42-aa10-c7cb5f8fd071")
    @Override
    public void setMultiplicityMin(String value) {
        setAttVal(((LinkEndSmClass)getClassOf()).getMultiplicityMinAtt(), value);
    }

    @objid ("58bbe56b-3844-4705-bcc1-2d43014f3a40")
    @Override
    public Link getLink() {
        Object obj = getDepVal(((LinkEndSmClass)getClassOf()).getLinkDep());
        return (obj instanceof Link)? (Link)obj : null;
    }

    @objid ("c10006e0-602e-449e-83fd-c08f69baba72")
    @Override
    public void setLink(Link value) {
        appendDepVal(((LinkEndSmClass)getClassOf()).getLinkDep(), (SmObjectImpl)value);
    }

    @objid ("4ef6ea5b-76b7-4abe-b04d-4776eac3838d")
    @Override
    public Instance getTarget() {
        Object obj = getDepVal(((LinkEndSmClass)getClassOf()).getTargetDep());
        return (obj instanceof Instance)? (Instance)obj : null;
    }

    @objid ("4b8a01a2-25d7-48d9-bfac-c13704b5a959")
    @Override
    public void setTarget(Instance value) {
        appendDepVal(((LinkEndSmClass)getClassOf()).getTargetDep(), (SmObjectImpl)value);
    }

    @objid ("5c67d3e4-9d54-4a6f-ba56-7e4ed67c5872")
    @Override
    public LinkEnd getOppositeOwner() {
        Object obj = getDepVal(((LinkEndSmClass)getClassOf()).getOppositeOwnerDep());
        return (obj instanceof LinkEnd)? (LinkEnd)obj : null;
    }

    @objid ("6dccc8cd-cf6a-4a62-b3b2-bd41e1e1f80a")
    @Override
    public void setOppositeOwner(LinkEnd value) {
        appendDepVal(((LinkEndSmClass)getClassOf()).getOppositeOwnerDep(), (SmObjectImpl)value);
    }

    @objid ("5fc2af3c-6864-4fad-afb2-cbbe4eff59ca")
    @Override
    public EList<InformationFlow> getRealizedInformationFlow() {
        return new SmList<>(this, ((LinkEndSmClass)getClassOf()).getRealizedInformationFlowDep());
    }

    @objid ("3c9c1b2c-83c4-4d86-97da-82ee03c8def6")
    @Override
    public <T extends InformationFlow> List<T> getRealizedInformationFlow(java.lang.Class<T> filterClass) {
        if (filterClass == null) {
          throw new IllegalArgumentException();
        }
        final List<T> results = new ArrayList<>();
        for (final InformationFlow element : getRealizedInformationFlow()) {
        	if (filterClass.isInstance(element)) {
        		results.add(filterClass.cast(element));
        	}
        }
        return Collections.unmodifiableList(results);
    }

    @objid ("6577f23b-d45e-4358-8e64-ceb7d19a1e8f")
    @Override
    public AssociationEnd getModel() {
        Object obj = getDepVal(((LinkEndSmClass)getClassOf()).getModelDep());
        return (obj instanceof AssociationEnd)? (AssociationEnd)obj : null;
    }

    @objid ("a88acaf7-48e8-43fb-8ce9-6580d9410331")
    @Override
    public void setModel(AssociationEnd value) {
        appendDepVal(((LinkEndSmClass)getClassOf()).getModelDep(), (SmObjectImpl)value);
    }

    @objid ("eb5715cd-30ef-4b57-97d5-213ddc81a4db")
    @Override
    public RequiredInterface getConsumer() {
        Object obj = getDepVal(((LinkEndSmClass)getClassOf()).getConsumerDep());
        return (obj instanceof RequiredInterface)? (RequiredInterface)obj : null;
    }

    @objid ("0451d8c9-fab4-44e3-870d-eb93975300af")
    @Override
    public void setConsumer(RequiredInterface value) {
        appendDepVal(((LinkEndSmClass)getClassOf()).getConsumerDep(), (SmObjectImpl)value);
    }

    @objid ("4a21918c-8f2e-4dd6-88cd-7dc799ed96a7")
    @Override
    public LinkEnd getOpposite() {
        Object obj = getDepVal(((LinkEndSmClass)getClassOf()).getOppositeDep());
        return (obj instanceof LinkEnd)? (LinkEnd)obj : null;
    }

    @objid ("c2e45a14-9ad6-42a3-ae3a-11514842cc28")
    @Override
    public void setOpposite(LinkEnd value) {
        appendDepVal(((LinkEndSmClass)getClassOf()).getOppositeDep(), (SmObjectImpl)value);
    }

    @objid ("86b477c5-c3d9-4a8a-a6aa-5539794de547")
    @Override
    public Instance getSource() {
        Object obj = getDepVal(((LinkEndSmClass)getClassOf()).getSourceDep());
        return (obj instanceof Instance)? (Instance)obj : null;
    }

    @objid ("fb1ce4ba-f179-4384-88bb-fbaaa71caa4d")
    @Override
    public void setSource(Instance value) {
        appendDepVal(((LinkEndSmClass)getClassOf()).getSourceDep(), (SmObjectImpl)value);
    }

    @objid ("decd255d-4676-40c1-b9f1-fb03787ca8b3")
    @Override
    public ProvidedInterface getProvider() {
        Object obj = getDepVal(((LinkEndSmClass)getClassOf()).getProviderDep());
        return (obj instanceof ProvidedInterface)? (ProvidedInterface)obj : null;
    }

    @objid ("8404bb2b-5af9-4c6e-bd9e-d340b21c8b4c")
    @Override
    public void setProvider(ProvidedInterface value) {
        appendDepVal(((LinkEndSmClass)getClassOf()).getProviderDep(), (SmObjectImpl)value);
    }

    @objid ("f1e42318-c150-4ed9-aa71-c67cc72b6ed6")
    @Override
    public Object accept(IModelVisitor v) {
        return v.visitLinkEnd(this);
    }

}
