/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.artifact;

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
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Artifact} with << UML2DeploymentSpecification >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a5115a85-757b-44ec-a53a-583442d3e59e")
public class UML2DeploymentSpecification {
    @objid ("31dd22f8-1849-42b3-bdd3-8f02b2664cf7")
    public static final String STEREOTYPE_NAME = "UML2DeploymentSpecification";

    /**
     * The underlying {@link Artifact} represented by this proxy, never null.
     */
    @objid ("d10c91ad-8803-49c5-8f85-a9598b72248e")
    protected final Artifact elt;

    /**
     * Tells whether a {@link UML2DeploymentSpecification proxy} can be instantiated from a {@link MObject} checking it is a {@link Artifact} stereotyped << UML2DeploymentSpecification >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1eccd5a5-8b72-4ab5-a845-34fd6853799e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Artifact) && ((Artifact) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2DeploymentSpecification.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Artifact} stereotyped << UML2DeploymentSpecification >> then instantiate a {@link UML2DeploymentSpecification} proxy.
     * 
     * @return a {@link UML2DeploymentSpecification} proxy on the created {@link Artifact}.
     */
    @objid ("fc045d88-fb2b-42be-96a6-57e8a77d453b")
    public static UML2DeploymentSpecification create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Artifact");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2DeploymentSpecification.STEREOTYPE_NAME);
        return UML2DeploymentSpecification.instantiate((Artifact)e);
    }

    /**
     * Tries to instantiate a {@link UML2DeploymentSpecification} proxy from a {@link Artifact} stereotyped << UML2DeploymentSpecification >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Artifact
     * @return a {@link UML2DeploymentSpecification} proxy or <i>null</i>.
     */
    @objid ("fd8385bb-9f22-440c-87e7-f9202c82b33f")
    public static UML2DeploymentSpecification instantiate(Artifact obj) {
        return UML2DeploymentSpecification.canInstantiate(obj) ? new UML2DeploymentSpecification(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2DeploymentSpecification} proxy from a {@link Artifact} stereotyped << UML2DeploymentSpecification >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Artifact}
     * @return a {@link UML2DeploymentSpecification} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("fb2ef5cf-b77a-481d-ba54-202a1c528b68")
    public static UML2DeploymentSpecification safeInstantiate(Artifact obj) throws IllegalArgumentException {
        if (UML2DeploymentSpecification.canInstantiate(obj))
        	return new UML2DeploymentSpecification(obj);
        else
        	throw new IllegalArgumentException("UML2DeploymentSpecification: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3248d9cd-f26d-4ec0-a97e-1def2bde238f")
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
        UML2DeploymentSpecification other = (UML2DeploymentSpecification) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Artifact}. 
     * @return the Artifact represented by this proxy, never null.
     */
    @objid ("9e3c2503-41c1-457d-af55-31177b59321f")
    public Artifact getElement() {
        return this.elt;
    }

    @objid ("a78fe164-76c5-4c85-b0cc-60bf26546498")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c9b5b068-e560-4990-837c-e6e62da0c6d3")
    protected UML2DeploymentSpecification(Artifact elt) {
        this.elt = elt;
    }

    @objid ("573ae85e-4d62-4b49-b9e5-9267a47f1a8d")
    public static final class MdaTypes {
        @objid ("07e620fd-2f3e-4f5c-8793-62be8c814002")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b4b6c2cb-610e-4c63-b4a6-38f575186cfe")
        private static Stereotype MDAASSOCDEP;

        @objid ("d01a0a7d-6021-460f-9945-3e4232c5aa74")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c639a43f-03e2-4538-8848-2aa881296c76")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "8c44d73d-5d0b-11df-a996-001302895b2b");
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
