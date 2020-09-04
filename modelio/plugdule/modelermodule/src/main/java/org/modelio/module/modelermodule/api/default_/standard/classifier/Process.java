/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.classifier;

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
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Classifier} with << process >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("2bf282d0-192b-4378-a060-395cb66630c9")
public class Process {
    @objid ("c8c21ba1-cb69-4b44-86b6-69f92e5b3a68")
    public static final String STEREOTYPE_NAME = "process";

    /**
     * The underlying {@link Classifier} represented by this proxy, never null.
     */
    @objid ("8e6ccc4f-df3c-483c-8004-a1a7749ef4e6")
    protected final Classifier elt;

    /**
     * Tells whether a {@link Process proxy} can be instantiated from a {@link MObject} checking it is a {@link Classifier} stereotyped << process >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0fb3ffc0-5c14-416f-92be-2b69196301ea")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Classifier) && ((Classifier) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Process.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Classifier} stereotyped << process >> then instantiate a {@link Process} proxy.
     * 
     * @return a {@link Process} proxy on the created {@link Classifier}.
     */
    @objid ("b334fa73-5f7a-4f68-a676-2ffe2d76718b")
    public static Process create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Classifier");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Process.STEREOTYPE_NAME);
        return Process.instantiate((Classifier)e);
    }

    /**
     * Tries to instantiate a {@link Process} proxy from a {@link Classifier} stereotyped << process >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Classifier
     * @return a {@link Process} proxy or <i>null</i>.
     */
    @objid ("abf33dcd-b080-444f-be22-ed8b57d3c18c")
    public static Process instantiate(Classifier obj) {
        return Process.canInstantiate(obj) ? new Process(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Process} proxy from a {@link Classifier} stereotyped << process >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Classifier}
     * @return a {@link Process} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d9c0ff4a-d53c-453f-925a-ae0c679155ad")
    public static Process safeInstantiate(Classifier obj) throws IllegalArgumentException {
        if (Process.canInstantiate(obj))
        	return new Process(obj);
        else
        	throw new IllegalArgumentException("Process: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2ba7d31d-e922-4182-8686-7748091070f3")
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
        Process other = (Process) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Classifier}. 
     * @return the Classifier represented by this proxy, never null.
     */
    @objid ("16c15985-e409-45a3-adf5-08f656e5ce85")
    public Classifier getElement() {
        return this.elt;
    }

    @objid ("2af8653a-4698-4389-80ac-ceb318bd4bff")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("5b066fb4-174e-4f81-81ca-9d01fa728dc2")
    protected Process(Classifier elt) {
        this.elt = elt;
    }

    @objid ("3f8020b0-6840-4956-b848-948e2dcce204")
    public static final class MdaTypes {
        @objid ("0eb8339d-c9c7-4c6a-81eb-de89036fb7ab")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("9c92491f-3ad2-4b41-8969-7a3e3ce1c803")
        private static Stereotype MDAASSOCDEP;

        @objid ("f05baddd-42b5-4f72-9887-d76f484798d4")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("628423ee-e123-45ad-8d8a-0764d07586ac")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01ef-0000-000000000000");
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
