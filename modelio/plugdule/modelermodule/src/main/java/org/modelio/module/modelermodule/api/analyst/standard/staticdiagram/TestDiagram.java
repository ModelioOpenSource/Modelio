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
 * Proxy class to handle a {@link StaticDiagram} with << test_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ff83f56b-339b-4fdb-b3f0-8f9a92979687")
public class TestDiagram {
    @objid ("7d5a55bd-b712-4cf3-9584-217fe5e5de61")
    public static final String STEREOTYPE_NAME = "test_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("cd609a3b-b9fb-4831-afa8-d50c17851743")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link TestDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << test_diagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("106c7741-4ef0-478e-9b51-5dd6fe133a92")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, TestDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << test_diagram >> then instantiate a {@link TestDiagram} proxy.
     * 
     * @return a {@link TestDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("859a28c5-2746-4e0a-afff-8a4f9aaa6d52")
    public static TestDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, TestDiagram.STEREOTYPE_NAME);
        return TestDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link TestDiagram} proxy from a {@link StaticDiagram} stereotyped << test_diagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link TestDiagram} proxy or <i>null</i>.
     */
    @objid ("279bac24-b5d4-40df-9c28-03707655d96b")
    public static TestDiagram instantiate(StaticDiagram obj) {
        return TestDiagram.canInstantiate(obj) ? new TestDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link TestDiagram} proxy from a {@link StaticDiagram} stereotyped << test_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link TestDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("908a6927-0231-4792-ba0f-7830e56e0059")
    public static TestDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (TestDiagram.canInstantiate(obj))
        	return new TestDiagram(obj);
        else
        	throw new IllegalArgumentException("TestDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9b8792b3-f40d-46a8-8b5c-b844079379a9")
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
        TestDiagram other = (TestDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("85ee5a45-d87b-45d2-9072-a650ebf64e7d")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("051a09b4-fae6-4298-8139-9d5a815a8da6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e73e5ac0-a25f-4eaf-8030-0caa2d0bada6")
    protected TestDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("3c996ab1-fae6-472e-98ca-526b7c97dbfe")
    public static final class MdaTypes {
        @objid ("d2ae8141-3b50-434e-8f33-ad22207b5388")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8ae3781e-f07e-4ad4-a01e-083cafa8bd06")
        private static Stereotype MDAASSOCDEP;

        @objid ("f75c138e-3aab-4f8b-97d3-5cc3bd6d2675")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0919ea3c-2f9f-421b-b594-b322d52b16b2")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e1d2f141-a387-4fd1-bff7-3f7dcbcb8718");
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
