/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.analyst.standard.staticdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.StaticDiagram;
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
 * Proxy class to handle a {@link StaticDiagram} with << goal_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("712e332b-fc86-4b4e-822a-eee5103016f9")
public class GoalDiagram {
    @objid ("91caa02e-ac14-43ab-820d-87a368ff6969")
    public static final String STEREOTYPE_NAME = "goal_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("3df54c77-ee50-4fc0-aad3-33faaf3eeab9")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link GoalDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << goal_diagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("722e2893-f797-45f7-8078-223534240e6f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, GoalDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << goal_diagram >> then instantiate a {@link GoalDiagram} proxy.
     * 
     * @return a {@link GoalDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("6bde22d3-53b8-4b59-9d38-81cd30132d4f")
    public static GoalDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, GoalDiagram.STEREOTYPE_NAME);
        return GoalDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link GoalDiagram} proxy from a {@link StaticDiagram} stereotyped << goal_diagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link GoalDiagram} proxy or <i>null</i>.
     */
    @objid ("49ca7280-23ae-45fb-829a-25a087dac8bf")
    public static GoalDiagram instantiate(StaticDiagram obj) {
        return GoalDiagram.canInstantiate(obj) ? new GoalDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link GoalDiagram} proxy from a {@link StaticDiagram} stereotyped << goal_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link GoalDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("32f087c8-3795-4add-b8de-97e6b6452aae")
    public static GoalDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (GoalDiagram.canInstantiate(obj))
        	return new GoalDiagram(obj);
        else
        	throw new IllegalArgumentException("GoalDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e9210bfd-3ec7-4c94-88ad-e5d4a400af6e")
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
        GoalDiagram other = (GoalDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("2e6368d7-4814-44f0-b39f-5f46dab025ba")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("cf193880-3af2-47a7-a1da-119415cd0d49")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e5142abb-4ee9-4063-8586-22fc1ebfba13")
    protected GoalDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("450e7de6-a2a6-41bf-9708-da4861532339")
    public static final class MdaTypes {
        @objid ("2a16f778-642f-4180-a114-821ef6bb0657")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e18e1550-327c-40b5-9047-629d2040d33f")
        private static Stereotype MDAASSOCDEP;

        @objid ("3ccc2ec7-41c0-4d2b-8200-e3804185bc21")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8cba6555-a13b-4115-8894-6b6aef21e65d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0a37-0000-000000000000");
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
