/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.usecasedependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
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
 * Proxy class to handle a {@link UseCaseDependency} with << include >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("09cedb92-5f5a-40aa-a318-488613b0c1ef")
public class Include {
    @objid ("29c863e6-d52e-426a-a73f-89b71e876aaa")
    public static final String STEREOTYPE_NAME = "include";

    /**
     * The underlying {@link UseCaseDependency} represented by this proxy, never null.
     */
    @objid ("b4351180-ec74-4057-abbe-073b13a5958a")
    protected final UseCaseDependency elt;

    /**
     * Tells whether a {@link Include proxy} can be instantiated from a {@link MObject} checking it is a {@link UseCaseDependency} stereotyped << include >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("57cda306-b0a8-4ec9-8127-113ad14139a8")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UseCaseDependency) && ((UseCaseDependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Include.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UseCaseDependency} stereotyped << include >> then instantiate a {@link Include} proxy.
     * 
     * @return a {@link Include} proxy on the created {@link UseCaseDependency}.
     */
    @objid ("0d2852c3-26d1-46ab-b2ae-d043f745486b")
    public static Include create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("UseCaseDependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Include.STEREOTYPE_NAME);
        return Include.instantiate((UseCaseDependency)e);
    }

    /**
     * Tries to instantiate a {@link Include} proxy from a {@link UseCaseDependency} stereotyped << include >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UseCaseDependency
     * @return a {@link Include} proxy or <i>null</i>.
     */
    @objid ("24850fae-18e5-4ba5-b031-60029afe4ee7")
    public static Include instantiate(UseCaseDependency obj) {
        return Include.canInstantiate(obj) ? new Include(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Include} proxy from a {@link UseCaseDependency} stereotyped << include >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link UseCaseDependency}
     * @return a {@link Include} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("fb848161-6080-44a9-9f7e-4ca8bb23e24d")
    public static Include safeInstantiate(UseCaseDependency obj) throws IllegalArgumentException {
        if (Include.canInstantiate(obj))
        	return new Include(obj);
        else
        	throw new IllegalArgumentException("Include: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("31b0a522-ce1a-4df7-8b24-6c9f7f74e577")
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
        Include other = (Include) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link UseCaseDependency}. 
     * @return the UseCaseDependency represented by this proxy, never null.
     */
    @objid ("71c137d4-c7c5-4cbf-b22a-61542ad07b0d")
    public UseCaseDependency getElement() {
        return this.elt;
    }

    @objid ("173f3798-4674-41a5-9e06-21f04f0ab050")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e415b2ff-150b-42a0-bd59-b603effe34e9")
    protected Include(UseCaseDependency elt) {
        this.elt = elt;
    }

    @objid ("9d68d730-b7bb-41e7-a89a-ad9aad3c697c")
    public static final class MdaTypes {
        @objid ("56357c70-42f3-4ced-bc04-ca6faec6fe17")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("265eaef8-6335-4f1a-b7c8-3705dee45909")
        private static Stereotype MDAASSOCDEP;

        @objid ("2b314142-998d-407a-ba7a-bed79e8ba66f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e2cfa4b1-2cf7-47b7-8d82-6ba8ed39b0cb")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00000000-0000-9c49-0000-000000000000");
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
