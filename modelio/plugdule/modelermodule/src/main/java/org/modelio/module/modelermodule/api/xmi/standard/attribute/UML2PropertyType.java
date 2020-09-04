/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.attribute;

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
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Attribute} with << UML2PropertyType >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("97813a21-b7b0-4858-b207-98c2879e16e9")
public class UML2PropertyType {
    @objid ("0e4c6a51-0156-4331-8a4a-fe4d1de680ad")
    public static final String STEREOTYPE_NAME = "UML2PropertyType";

    @objid ("daa2861d-3877-4b4a-aca6-0188227f8a80")
    public static final String PROPERTYTYPE_TAGTYPE = "PropertyType";

    /**
     * The underlying {@link Attribute} represented by this proxy, never null.
     */
    @objid ("3704a9fe-30a2-4663-b866-b722298608cb")
    protected final Attribute elt;

    /**
     * Tells whether a {@link UML2PropertyType proxy} can be instantiated from a {@link MObject} checking it is a {@link Attribute} stereotyped << UML2PropertyType >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("ea60eae9-7176-46ec-afa2-f49876334d98")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Attribute) && ((Attribute) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2PropertyType.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Attribute} stereotyped << UML2PropertyType >> then instantiate a {@link UML2PropertyType} proxy.
     * 
     * @return a {@link UML2PropertyType} proxy on the created {@link Attribute}.
     */
    @objid ("492e6986-30c5-4537-9010-993de94c5e41")
    public static UML2PropertyType create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Attribute");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2PropertyType.STEREOTYPE_NAME);
        return UML2PropertyType.instantiate((Attribute)e);
    }

    /**
     * Tries to instantiate a {@link UML2PropertyType} proxy from a {@link Attribute} stereotyped << UML2PropertyType >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Attribute
     * @return a {@link UML2PropertyType} proxy or <i>null</i>.
     */
    @objid ("7d4b0b27-bafe-4162-ae21-dbfb353c48df")
    public static UML2PropertyType instantiate(Attribute obj) {
        return UML2PropertyType.canInstantiate(obj) ? new UML2PropertyType(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2PropertyType} proxy from a {@link Attribute} stereotyped << UML2PropertyType >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Attribute}
     * @return a {@link UML2PropertyType} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("fc243cd1-6f4f-4cc1-b2d2-472a649724da")
    public static UML2PropertyType safeInstantiate(Attribute obj) throws IllegalArgumentException {
        if (UML2PropertyType.canInstantiate(obj))
        	return new UML2PropertyType(obj);
        else
        	throw new IllegalArgumentException("UML2PropertyType: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b9ca0f3c-e505-455b-8480-c277c85c695c")
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
        UML2PropertyType other = (UML2PropertyType) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Attribute}. 
     * @return the Attribute represented by this proxy, never null.
     */
    @objid ("462bfb17-9c18-4001-a670-fec074f5b26e")
    public Attribute getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'PropertyType'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("d6a8b817-f912-47ea-98ba-f5aeb84be746")
    public String getPropertyType() {
        return this.elt.getTagValue(UML2PropertyType.MdaTypes.PROPERTYTYPE_TAGTYPE_ELT);
    }

    @objid ("d7be22a4-908b-4fee-b774-350fcf15204b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'PropertyType'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("8ffb331e-bbdb-4034-bc03-fff0c6fd2d9b")
    public void setPropertyType(String value) {
        this.elt.putTagValue(UML2PropertyType.MdaTypes.PROPERTYTYPE_TAGTYPE_ELT, value);
    }

    @objid ("3a2da431-20bb-4947-84f0-fa49ed4c5be7")
    protected UML2PropertyType(Attribute elt) {
        this.elt = elt;
    }

    @objid ("52f01be1-c820-4bae-a974-82d910c67b9a")
    public static final class MdaTypes {
        @objid ("ad5d1c78-1c63-4917-8693-b18266688924")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c4cdf15a-4016-4557-bb6d-0d7b3c53c6a7")
        public static TagType PROPERTYTYPE_TAGTYPE_ELT;

        @objid ("a3a971b3-9761-4adb-aec4-9eb29a033386")
        private static Stereotype MDAASSOCDEP;

        @objid ("f0dc23e1-5b17-491a-b101-2deec4f7224d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8c427100-518b-46eb-bcff-40ea9baa3a00")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "68c63e60-70d6-11e0-872f-0027103f347c");
            PROPERTYTYPE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "68c63e61-70d6-11e0-872f-0027103f347c");
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
