/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << trace >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("deb1d430-370d-47a6-83f9-89eae36b4475")
public class Trace {
    @objid ("b289a4e4-4baa-4354-aa65-ddf99b05f1f2")
    public static final String STEREOTYPE_NAME = "trace";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("997181f9-efd3-4d86-926b-bd264eabbe23")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Trace proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << trace >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c06c31c1-ff3f-4feb-b297-0d496491a331")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Trace.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << trace >> then instantiate a {@link Trace} proxy.
     * 
     * @return a {@link Trace} proxy on the created {@link Dependency}.
     */
    @objid ("c35418be-ea37-4af5-9992-667ac3f6d899")
    public static Trace create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Trace.STEREOTYPE_NAME);
        return Trace.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Trace} proxy from a {@link Dependency} stereotyped << trace >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Trace} proxy or <i>null</i>.
     */
    @objid ("97873eea-3aba-4eb8-8045-d92535d20359")
    public static Trace instantiate(Dependency obj) {
        return Trace.canInstantiate(obj) ? new Trace(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Trace} proxy from a {@link Dependency} stereotyped << trace >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Trace} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9d1f6088-54f3-431b-a511-ede6b10a2795")
    public static Trace safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Trace.canInstantiate(obj))
        	return new Trace(obj);
        else
        	throw new IllegalArgumentException("Trace: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("7a7623af-fd3e-4f26-8055-625e54ddf14e")
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
        Trace other = (Trace) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("d60a90a0-7d08-4aa9-a719-21e45f2f4115")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("c3218a97-6a64-49ee-803d-7ea2348e794b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("28a42329-3bd9-4df2-84f1-b4376da61742")
    protected Trace(Dependency elt) {
        this.elt = elt;
    }

    @objid ("1335f40a-5185-493c-ac4b-6745cea1855f")
    public static final class MdaTypes {
        @objid ("80400d70-239a-4b0e-b6ba-360cfaf389cf")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ad0e28a5-bac0-4c54-9c65-59b0933d7e22")
        private static Stereotype MDAASSOCDEP;

        @objid ("bb85f1d6-ff2b-4956-8f72-65142b77b6a4")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8a049c8d-8045-4764-946f-aa34ae7bfbb5")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01280500-0000-0b37-0000-000000000000");
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
