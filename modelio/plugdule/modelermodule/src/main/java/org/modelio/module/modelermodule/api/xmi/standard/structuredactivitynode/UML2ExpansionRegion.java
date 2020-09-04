/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.structuredactivitynode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.StructuredActivityNode;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link StructuredActivityNode} with << UML2ExpansionRegion >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("7e99c219-46f9-4244-8fb2-b762819d70db")
public class UML2ExpansionRegion {
    @objid ("7ca45c3e-7ba4-496a-967e-47c644c30465")
    public static final String STEREOTYPE_NAME = "UML2ExpansionRegion";

    @objid ("4f5c5f0e-2209-4c3a-bee4-3f67952f8438")
    public static final String MODE_TAGTYPE = "Mode";

    /**
     * The underlying {@link StructuredActivityNode} represented by this proxy, never null.
     */
    @objid ("d1f32ece-3c4c-4928-802f-f62cd3462c54")
    protected final StructuredActivityNode elt;

    /**
     * Tells whether a {@link UML2ExpansionRegion proxy} can be instantiated from a {@link MObject} checking it is a {@link StructuredActivityNode} stereotyped << UML2ExpansionRegion >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("882acb93-b665-481c-88a4-d2e88676a9ae")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StructuredActivityNode) && ((StructuredActivityNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExpansionRegion.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StructuredActivityNode} stereotyped << UML2ExpansionRegion >> then instantiate a {@link UML2ExpansionRegion} proxy.
     * 
     * @return a {@link UML2ExpansionRegion} proxy on the created {@link StructuredActivityNode}.
     */
    @objid ("aecee920-3907-4942-9008-362123e27870")
    public static UML2ExpansionRegion create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StructuredActivityNode");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExpansionRegion.STEREOTYPE_NAME);
        return UML2ExpansionRegion.instantiate((StructuredActivityNode)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExpansionRegion} proxy from a {@link StructuredActivityNode} stereotyped << UML2ExpansionRegion >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StructuredActivityNode
     * @return a {@link UML2ExpansionRegion} proxy or <i>null</i>.
     */
    @objid ("b08fe37b-6cb5-4051-bd63-44ed0cf1ffaf")
    public static UML2ExpansionRegion instantiate(StructuredActivityNode obj) {
        return UML2ExpansionRegion.canInstantiate(obj) ? new UML2ExpansionRegion(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExpansionRegion} proxy from a {@link StructuredActivityNode} stereotyped << UML2ExpansionRegion >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StructuredActivityNode}
     * @return a {@link UML2ExpansionRegion} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d2afa304-5ea6-48ed-bdc8-b7665caaa43c")
    public static UML2ExpansionRegion safeInstantiate(StructuredActivityNode obj) throws IllegalArgumentException {
        if (UML2ExpansionRegion.canInstantiate(obj))
        	return new UML2ExpansionRegion(obj);
        else
        	throw new IllegalArgumentException("UML2ExpansionRegion: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("54687e11-e404-4431-b7c9-1c58789c41a2")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UML2ExpansionRegion other = (UML2ExpansionRegion) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StructuredActivityNode}. 
     * @return the StructuredActivityNode represented by this proxy, never null.
     */
    @objid ("cb688f00-6c02-4596-b303-1aab5a9ed97e")
    public StructuredActivityNode getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Mode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("38bf4d38-91b5-42e6-9599-217478a6d84d")
    public String getMode() {
        return this.elt.getTagValue(UML2ExpansionRegion.MdaTypes.MODE_TAGTYPE_ELT);
    }

    @objid ("e7290a9f-cc8b-49b7-b1fc-a1cbc1413b83")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'Mode'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("8538207e-6494-4039-9776-25defcf2ce92")
    public void setMode(String value) {
        this.elt.putTagValue(UML2ExpansionRegion.MdaTypes.MODE_TAGTYPE_ELT, value);
    }

    @objid ("2b19e77c-09be-46d0-9d9a-c67a136bd50b")
    protected UML2ExpansionRegion(StructuredActivityNode elt) {
        this.elt = elt;
    }

    @objid ("1ec99520-b74d-45f5-9edb-594801c80054")
    public static final class MdaTypes {
        @objid ("fb1e6e96-e006-475c-b780-c72fb4b4973f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e588db86-c60a-4e67-83b6-280aed3e39d4")
        public static TagType MODE_TAGTYPE_ELT;

        @objid ("0669fe16-487c-4901-8f0e-fe7d689304ba")
        private static Stereotype MDAASSOCDEP;

        @objid ("b9924795-3617-4888-8f19-fa4c0d6e9a6e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0d19a160-2bb9-4995-90f5-8a99a766ba97")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "07111319-1fd7-11df-948e-001302895b2b");
            MODE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "5930231e-3117-11df-b4ad-001302895b2b");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
        }


	static {
		if(ModelerModuleModule.getInstance() != null) {
			init(ModelerModuleModule.getInstance().getModuleContext());
		}
	}
    }

}
