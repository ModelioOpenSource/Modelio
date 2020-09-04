/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.node;

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
import org.modelio.metamodel.uml.statik.Node;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Node} with << UML2ExecutionEnvironment >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("c91962aa-081b-4abf-ad28-673f0b5e2aa7")
public class UML2ExecutionEnvironment {
    @objid ("5e45e6f3-de4e-4fcd-a5a8-86fd0fc03cab")
    public static final String STEREOTYPE_NAME = "UML2ExecutionEnvironment";

    /**
     * The underlying {@link Node} represented by this proxy, never null.
     */
    @objid ("94f8009a-1add-4a36-9561-ded5676cd3cf")
    protected final Node elt;

    /**
     * Tells whether a {@link UML2ExecutionEnvironment proxy} can be instantiated from a {@link MObject} checking it is a {@link Node} stereotyped << UML2ExecutionEnvironment >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("917838ff-b5bd-4d4c-9187-1aa1bf8add82")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Node) && ((Node) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExecutionEnvironment.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Node} stereotyped << UML2ExecutionEnvironment >> then instantiate a {@link UML2ExecutionEnvironment} proxy.
     * 
     * @return a {@link UML2ExecutionEnvironment} proxy on the created {@link Node}.
     */
    @objid ("3fa39d36-b8cb-4f88-8046-ce0744b709b4")
    public static UML2ExecutionEnvironment create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Node");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExecutionEnvironment.STEREOTYPE_NAME);
        return UML2ExecutionEnvironment.instantiate((Node)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExecutionEnvironment} proxy from a {@link Node} stereotyped << UML2ExecutionEnvironment >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Node
     * @return a {@link UML2ExecutionEnvironment} proxy or <i>null</i>.
     */
    @objid ("ff2ed444-2ed3-436d-aaff-36dbe31b2943")
    public static UML2ExecutionEnvironment instantiate(Node obj) {
        return UML2ExecutionEnvironment.canInstantiate(obj) ? new UML2ExecutionEnvironment(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExecutionEnvironment} proxy from a {@link Node} stereotyped << UML2ExecutionEnvironment >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Node}
     * @return a {@link UML2ExecutionEnvironment} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("cdedbf06-2d5f-428f-a592-8af62a249c7c")
    public static UML2ExecutionEnvironment safeInstantiate(Node obj) throws IllegalArgumentException {
        if (UML2ExecutionEnvironment.canInstantiate(obj))
        	return new UML2ExecutionEnvironment(obj);
        else
        	throw new IllegalArgumentException("UML2ExecutionEnvironment: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("89752122-780f-4283-aea3-fe6d4fb32744")
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
        UML2ExecutionEnvironment other = (UML2ExecutionEnvironment) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Node}. 
     * @return the Node represented by this proxy, never null.
     */
    @objid ("99c477af-2d10-43fb-a3a4-cb240417cbef")
    public Node getElement() {
        return this.elt;
    }

    @objid ("0c46f316-3709-4b05-97cc-389696172cce")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7979ceef-ca93-4568-baa2-b77f6dbd32b4")
    protected UML2ExecutionEnvironment(Node elt) {
        this.elt = elt;
    }

    @objid ("83082363-6983-47b6-9176-ae729c96eb16")
    public static final class MdaTypes {
        @objid ("d48ddb4e-9e74-4fc6-82ce-cdce0b44822b")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2cc5c95f-f752-40e1-82b7-64432dcf4436")
        private static Stereotype MDAASSOCDEP;

        @objid ("d4153eba-fac2-4dda-b815-4786eedf296c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("daa16740-d2d0-4644-b0bf-421d3f49facc")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "8e76c95f-5821-11df-be59-001302895b2b");
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
