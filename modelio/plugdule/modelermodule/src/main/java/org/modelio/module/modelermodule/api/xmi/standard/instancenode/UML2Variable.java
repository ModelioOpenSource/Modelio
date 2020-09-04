/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.instancenode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.InstanceNode;
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
 * Proxy class to handle a {@link InstanceNode} with << UML2Variable >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a009a5d3-00b8-46b7-ad7a-a22b170ab56e")
public class UML2Variable {
    @objid ("9973f0fc-c575-4b75-a79d-8971ae83abff")
    public static final String STEREOTYPE_NAME = "UML2Variable";

    @objid ("d2b4586a-6ab2-4c5f-bfc7-189ff520bf36")
    public static final String LOWER_TAGTYPE = "Lower";

    @objid ("6b707afb-c98a-4a4f-ba81-a1bc3d2a0708")
    public static final String ORDERED_TAGTYPE = "Ordered";

    @objid ("786da10f-5782-4024-abdd-939786b1be1b")
    public static final String UNIQUE_TAGTYPE = "Unique";

    /**
     * The underlying {@link InstanceNode} represented by this proxy, never null.
     */
    @objid ("06c4bf7b-27b5-44fa-8dc0-b897617d9b5e")
    protected final InstanceNode elt;

    /**
     * Tells whether a {@link UML2Variable proxy} can be instantiated from a {@link MObject} checking it is a {@link InstanceNode} stereotyped << UML2Variable >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("08ce331d-fe31-41d0-a960-50e7ec997f9e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof InstanceNode) && ((InstanceNode) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Variable.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link InstanceNode} stereotyped << UML2Variable >> then instantiate a {@link UML2Variable} proxy.
     * 
     * @return a {@link UML2Variable} proxy on the created {@link InstanceNode}.
     */
    @objid ("7824145e-c49c-4a5f-a02a-7420454ce207")
    public static UML2Variable create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("InstanceNode");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Variable.STEREOTYPE_NAME);
        return UML2Variable.instantiate((InstanceNode)e);
    }

    /**
     * Tries to instantiate a {@link UML2Variable} proxy from a {@link InstanceNode} stereotyped << UML2Variable >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a InstanceNode
     * @return a {@link UML2Variable} proxy or <i>null</i>.
     */
    @objid ("037b4732-3601-4903-afc9-556de61ca482")
    public static UML2Variable instantiate(InstanceNode obj) {
        return UML2Variable.canInstantiate(obj) ? new UML2Variable(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Variable} proxy from a {@link InstanceNode} stereotyped << UML2Variable >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link InstanceNode}
     * @return a {@link UML2Variable} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("c01214e0-d1ea-46d4-95d7-6f5488b1e04a")
    public static UML2Variable safeInstantiate(InstanceNode obj) throws IllegalArgumentException {
        if (UML2Variable.canInstantiate(obj))
        	return new UML2Variable(obj);
        else
        	throw new IllegalArgumentException("UML2Variable: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("778c7308-6919-4667-af8d-8dfa39b299fd")
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
        UML2Variable other = (UML2Variable) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link InstanceNode}. 
     * @return the InstanceNode represented by this proxy, never null.
     */
    @objid ("6872dd26-e6cf-4532-987a-e701573aadd8")
    public InstanceNode getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Lower'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("58977701-67cc-43e2-8702-2ab9f4a26922")
    public String getLower() {
        return this.elt.getTagValue(UML2Variable.MdaTypes.LOWER_TAGTYPE_ELT);
    }

    @objid ("d6ce5a37-0920-480f-b1bf-775d6ceb616b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Getter for boolean property 'Ordered'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("a46e02b2-36aa-486d-bcb7-43f084ba0e6c")
    public boolean isOrdered() {
        return this.elt.isTagged(UML2Variable.MdaTypes.ORDERED_TAGTYPE_ELT);
    }

    /**
     * Getter for boolean property 'Unique'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("65c17db1-c05d-4ef1-9fe0-7e3a14265bac")
    public boolean isUnique() {
        return this.elt.isTagged(UML2Variable.MdaTypes.UNIQUE_TAGTYPE_ELT);
    }

    /**
     * Setter for string property 'Lower'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("0797910b-6fdb-49a0-a24d-8a6b48b2360b")
    public void setLower(String value) {
        this.elt.putTagValue(UML2Variable.MdaTypes.LOWER_TAGTYPE_ELT, value);
    }

    /**
     * Setter for boolean property 'Ordered'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("521b808f-3e8e-4e3a-a36e-14f488bfa7e1")
    public void setOrdered(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(UML2Variable.MdaTypes.ORDERED_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(UML2Variable.MdaTypes.ORDERED_TAGTYPE_ELT);
    }

    /**
     * Setter for boolean property 'Unique'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("92098bba-6395-4ffb-b797-4e11aa84650e")
    public void setUnique(boolean value) {
        if (value)
          ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createTaggedValue(UML2Variable.MdaTypes.UNIQUE_TAGTYPE_ELT, this.elt);
        else
          this.elt.removeTags(UML2Variable.MdaTypes.UNIQUE_TAGTYPE_ELT);
    }

    @objid ("565e8878-22e0-4a18-88a8-e45673854334")
    protected UML2Variable(InstanceNode elt) {
        this.elt = elt;
    }

    @objid ("cde1a32f-53c8-43d5-bcef-de80f475ded2")
    public static final class MdaTypes {
        @objid ("9ba21046-4da9-4897-a516-887512ea1000")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0804c5af-94e5-4ce6-b8f1-17c899ed6800")
        public static TagType ORDERED_TAGTYPE_ELT;

        @objid ("8704a7be-65e5-4d1f-8abd-0981acd550a6")
        public static TagType UNIQUE_TAGTYPE_ELT;

        @objid ("34992208-65dd-49cf-98c5-4ab696edfd2d")
        public static TagType LOWER_TAGTYPE_ELT;

        @objid ("bfc31385-5047-410a-84c0-a582450413ad")
        private static Stereotype MDAASSOCDEP;

        @objid ("1262e15e-139e-4a1b-88f4-be3029ad9c2d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a8381902-3880-47fa-be00-faae30ef9685")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "de38398e-c746-11e1-8624-0027103f347d");
            ORDERED_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "03791167-cf3b-11e1-928c-0027103f347d");
            UNIQUE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "16933d11-cf40-11e1-b123-0027103f347d");
            LOWER_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "269fc5ec-cf40-11e1-b123-0027103f347d");
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
