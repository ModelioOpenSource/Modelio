/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.umlmodelelement;

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
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link UmlModelElement} with << ModelComponentElementAlias >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("3f02a222-93eb-462f-b374-436e42fd6818")
public class ModelComponentElementAlias {
    @objid ("1f3f474d-1f23-4507-8379-be14f2b6e71a")
    public static final String STEREOTYPE_NAME = "ModelComponentElementAlias";

    @objid ("5488d04a-0ad9-4ca6-b459-523a20d46d1f")
    public static final String UUID_TAGTYPE = "uuid";

    /**
     * The underlying {@link UmlModelElement} represented by this proxy, never null.
     */
    @objid ("38732fc4-fe6d-4486-99c2-a411329fa893")
    protected final UmlModelElement elt;

    /**
     * Tells whether a {@link ModelComponentElementAlias proxy} can be instantiated from a {@link MObject} checking it is a {@link UmlModelElement} stereotyped << ModelComponentElementAlias >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("853cdf8e-915b-4199-b5b5-6dc917fc42f1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UmlModelElement) && ((UmlModelElement) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ModelComponentElementAlias.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UmlModelElement} stereotyped << ModelComponentElementAlias >> then instantiate a {@link ModelComponentElementAlias} proxy.
     * 
     * @return a {@link ModelComponentElementAlias} proxy on the created {@link UmlModelElement}.
     */
    @objid ("00333dff-3c34-4bee-9f89-98b83a536123")
    public static ModelComponentElementAlias create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("UmlModelElement");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ModelComponentElementAlias.STEREOTYPE_NAME);
        return ModelComponentElementAlias.instantiate((UmlModelElement)e);
    }

    /**
     * Tries to instantiate a {@link ModelComponentElementAlias} proxy from a {@link UmlModelElement} stereotyped << ModelComponentElementAlias >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UmlModelElement
     * @return a {@link ModelComponentElementAlias} proxy or <i>null</i>.
     */
    @objid ("80cb42a7-e6cf-45d1-9afd-fe545a046563")
    public static ModelComponentElementAlias instantiate(UmlModelElement obj) {
        return ModelComponentElementAlias.canInstantiate(obj) ? new ModelComponentElementAlias(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ModelComponentElementAlias} proxy from a {@link UmlModelElement} stereotyped << ModelComponentElementAlias >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link UmlModelElement}
     * @return a {@link ModelComponentElementAlias} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a49c87ae-0c6d-46d4-9a3e-a1ab4a55dfa4")
    public static ModelComponentElementAlias safeInstantiate(UmlModelElement obj) throws IllegalArgumentException {
        if (ModelComponentElementAlias.canInstantiate(obj))
        	return new ModelComponentElementAlias(obj);
        else
        	throw new IllegalArgumentException("ModelComponentElementAlias: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("98bf33ab-31f3-467d-a5a1-c0376574c7d2")
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
        ModelComponentElementAlias other = (ModelComponentElementAlias) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link UmlModelElement}. 
     * @return the UmlModelElement represented by this proxy, never null.
     */
    @objid ("508a93d2-da25-4a70-8e4d-95f9c2ca43b1")
    public UmlModelElement getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'uuid'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("3f93b1ef-7165-449a-99eb-8937349c56c0")
    public String getUuid() {
        return this.elt.getTagValue(ModelComponentElementAlias.MdaTypes.UUID_TAGTYPE_ELT);
    }

    @objid ("ad601a97-fe48-4189-9143-0df903986f72")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'uuid'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("acb3d0d2-8f32-4dc7-8161-b6587683fea5")
    public void setUuid(String value) {
        this.elt.putTagValue(ModelComponentElementAlias.MdaTypes.UUID_TAGTYPE_ELT, value);
    }

    @objid ("77d988f8-9ee1-4221-bdfe-3374066ac6a0")
    protected ModelComponentElementAlias(UmlModelElement elt) {
        this.elt = elt;
    }

    @objid ("5d98506b-4aa3-4855-9a1e-78d997c2836c")
    public static final class MdaTypes {
        @objid ("0e93cf78-f777-40c0-abec-b45e2f38cd2c")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("556907c2-56ba-49c1-87c7-4d891f166c07")
        public static TagType UUID_TAGTYPE_ELT;

        @objid ("04e69a79-25b0-41da-bfee-9a7a668f3101")
        private static Stereotype MDAASSOCDEP;

        @objid ("ee2ab505-64ad-46a0-b92d-d6606a2d003d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("cd000b88-15b7-4689-aa91-aaf74332a927")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "3254be34-c7d8-4018-8a68-5de65c30b773");
            UUID_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "7ebbd53f-a5f3-433b-872c-4024615af229");
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
