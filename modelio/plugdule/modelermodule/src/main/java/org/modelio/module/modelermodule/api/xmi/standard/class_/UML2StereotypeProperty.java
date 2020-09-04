/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.class_;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Class} with << UML2StereotypeProperty >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("16847bf6-c61c-4c56-9ab6-52148bf70507")
public class UML2StereotypeProperty {
    @objid ("6c49eafc-d7fc-4743-a7ce-d88b6b663ab0")
    public static final String STEREOTYPE_NAME = "UML2StereotypeProperty";

    @objid ("adbea322-df4b-40c1-bb1b-504e6f8040bc")
    public static final String PROPERTY_TAGTYPE = "Property";

    /**
     * The underlying {@link Class} represented by this proxy, never null.
     */
    @objid ("30972ea3-e9e5-4e22-a2df-21642ced3dcb")
    protected final Class elt;

    /**
     * Tells whether a {@link UML2StereotypeProperty proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << UML2StereotypeProperty >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("431f391f-9368-4d9c-9297-65c8d511a21a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Class) && ((Class) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2StereotypeProperty.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Class} stereotyped << UML2StereotypeProperty >> then instantiate a {@link UML2StereotypeProperty} proxy.
     * 
     * @return a {@link UML2StereotypeProperty} proxy on the created {@link Class}.
     */
    @objid ("3f382ec1-4f09-43f5-a2e4-ed6cb96466c1")
    public static UML2StereotypeProperty create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Class");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2StereotypeProperty.STEREOTYPE_NAME);
        return UML2StereotypeProperty.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link UML2StereotypeProperty} proxy from a {@link Class} stereotyped << UML2StereotypeProperty >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link UML2StereotypeProperty} proxy or <i>null</i>.
     */
    @objid ("45dafea2-c335-4544-a9b6-40d1cfc50ce8")
    public static UML2StereotypeProperty instantiate(Class obj) {
        return UML2StereotypeProperty.canInstantiate(obj) ? new UML2StereotypeProperty(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2StereotypeProperty} proxy from a {@link Class} stereotyped << UML2StereotypeProperty >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Class}
     * @return a {@link UML2StereotypeProperty} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a4d90208-dec8-4436-b705-5e30a46b06c0")
    public static UML2StereotypeProperty safeInstantiate(Class obj) throws IllegalArgumentException {
        if (UML2StereotypeProperty.canInstantiate(obj))
        	return new UML2StereotypeProperty(obj);
        else
        	throw new IllegalArgumentException("UML2StereotypeProperty: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9174827b-fa7d-41b7-a0bb-9588c885e491")
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
        UML2StereotypeProperty other = (UML2StereotypeProperty) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Class}. 
     * @return the Class represented by this proxy, never null.
     */
    @objid ("d87495e8-7aab-4edf-b446-e1f740813abe")
    public Class getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Property'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("b8a69ecc-2e8d-41b3-8307-4d114fe19224")
    public String getProperty() {
        return this.elt.getTagValue(UML2StereotypeProperty.MdaTypes.PROPERTY_TAGTYPE_ELT);
    }

    @objid ("b65629f6-9bf6-4baa-b869-37460f2b00eb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'Property'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("dd432cf5-8841-4a37-ac26-c2e7c8464157")
    public void setProperty(String value) {
        this.elt.putTagValue(UML2StereotypeProperty.MdaTypes.PROPERTY_TAGTYPE_ELT, value);
    }

    @objid ("4ff61f2c-9d87-4c08-ab06-ca99f454e1f2")
    protected UML2StereotypeProperty(Class elt) {
        this.elt = elt;
    }

    @objid ("3e2c97cc-5fc7-4642-8c2c-e5591f0c6d83")
    public static final class MdaTypes {
        @objid ("d147cedb-5e48-4ab9-86f5-5d237fc9eabb")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5a38c61c-a520-40ed-b989-04c67bcf0e0c")
        public static TagType PROPERTY_TAGTYPE_ELT;

        @objid ("405dcda0-699e-44d2-91ec-af881f754f14")
        private static Stereotype MDAASSOCDEP;

        @objid ("e951a9f5-12d7-464d-a233-6fe08a3785fe")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4ffdf31b-8822-416b-a74c-9d92dee5464d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "bae91a3b-7009-11e0-a462-0027103f347c");
            PROPERTY_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "c28e8b06-7009-11e0-a462-0027103f347c");
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
