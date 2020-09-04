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
 * Proxy class to handle a {@link UseCaseDependency} with << extend >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5babb04d-9057-43e4-9e92-b0706d209ff8")
public class Extend {
    @objid ("8493d80c-34c0-4d07-9adf-b06e13aeec5d")
    public static final String STEREOTYPE_NAME = "extend";

    /**
     * The underlying {@link UseCaseDependency} represented by this proxy, never null.
     */
    @objid ("cb96beb6-07dd-4e80-942d-04dd0b1a0f00")
    protected final UseCaseDependency elt;

    /**
     * Tells whether a {@link Extend proxy} can be instantiated from a {@link MObject} checking it is a {@link UseCaseDependency} stereotyped << extend >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6cb2b21c-5476-42a2-ac50-5b0b957a5397")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof UseCaseDependency) && ((UseCaseDependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Extend.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link UseCaseDependency} stereotyped << extend >> then instantiate a {@link Extend} proxy.
     * 
     * @return a {@link Extend} proxy on the created {@link UseCaseDependency}.
     */
    @objid ("5a73dc02-296a-4465-b07c-5519e1799394")
    public static Extend create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("UseCaseDependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Extend.STEREOTYPE_NAME);
        return Extend.instantiate((UseCaseDependency)e);
    }

    /**
     * Tries to instantiate a {@link Extend} proxy from a {@link UseCaseDependency} stereotyped << extend >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a UseCaseDependency
     * @return a {@link Extend} proxy or <i>null</i>.
     */
    @objid ("820d2ba1-fa03-4226-b2c1-394d7072f54f")
    public static Extend instantiate(UseCaseDependency obj) {
        return Extend.canInstantiate(obj) ? new Extend(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Extend} proxy from a {@link UseCaseDependency} stereotyped << extend >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link UseCaseDependency}
     * @return a {@link Extend} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9bb2593b-b125-4cbe-8ad0-b61873836d2b")
    public static Extend safeInstantiate(UseCaseDependency obj) throws IllegalArgumentException {
        if (Extend.canInstantiate(obj))
        	return new Extend(obj);
        else
        	throw new IllegalArgumentException("Extend: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("03c582b0-4663-4bc5-bad8-7cc9798e687f")
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
        Extend other = (Extend) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link UseCaseDependency}. 
     * @return the UseCaseDependency represented by this proxy, never null.
     */
    @objid ("099cebce-f617-46b8-80f5-39242d4925b1")
    public UseCaseDependency getElement() {
        return this.elt;
    }

    @objid ("e571c23c-27b5-4072-a48a-e10dbfba0e1a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2bd2eb03-3875-4c49-a5d3-5e2a0d83ab50")
    protected Extend(UseCaseDependency elt) {
        this.elt = elt;
    }

    @objid ("c8564055-bb69-4426-9956-d7b941a73e96")
    public static final class MdaTypes {
        @objid ("6ab16d36-7fef-4b3e-af61-f3e0d154a54b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("71ed03af-9f11-499a-aff2-45f740c7bdeb")
        private static Stereotype MDAASSOCDEP;

        @objid ("a664da44-b0d3-4e63-957f-f249fb7b81c7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2b52bdb9-5740-4bcb-b7a4-f6509731a833")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00000000-0000-9c48-0000-000000000000");
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
