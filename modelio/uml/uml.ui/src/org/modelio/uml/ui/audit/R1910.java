/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.uml.ui.audit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.audit.engine.core.AbstractControl;
import org.modelio.audit.engine.core.AbstractRule;
import org.modelio.audit.engine.core.AuditEntry;
import org.modelio.audit.engine.core.IAuditEntry;
import org.modelio.audit.engine.core.IAuditExecutionPlan.AuditTrigger;
import org.modelio.audit.engine.core.IControl;
import org.modelio.audit.engine.core.IDiagnosticCollector;
import org.modelio.audit.engine.core.IRule;
import org.modelio.audit.service.AuditSeverity;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.uml.ui.plugin.UmlUi;
import org.modelio.vcore.session.api.model.change.IElementMovedEvent;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Rule implementation origin: LinkChecker checkLinkName
 */
@objid ("e0080a8a-6e3c-4880-8fea-7d12c50e3f8d")
public class R1910 extends AbstractUmlRule {
    @objid ("0822eb2c-09e2-4590-980d-9541a680363c")
    private static final String RULEID = "R1910";

    /**
     * The checker unique instance. Remove it if you are not using a unique checker strategy.<br>
     * 
     * @see AbstractRule#getCreationControl(IElement)
     * @see AbstractRule#getUpdateControl(IElement)
     * @see AbstractRule#getMoveControl(IElementMovedEvent)
     */
    @objid ("f3ee7bac-adab-4abc-a10c-833f80391219")
    private CheckR1910 checkerInstance = null;

    @objid ("5d9db9d1-c8aa-4072-bff3-31b047c50636")
    @Override
    public String getRuleId() {
        return R1910.RULEID;
    }

    @objid ("a6ab387a-eec1-434c-b168-a51e44baa782")
    @Override
    public void autoRegister(UmlAuditPlan plan) {
        plan.registerRule(Link.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(Association.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(LinkEnd.MQNAME, this, AuditTrigger.UPDATE);
        plan.registerRule(AssociationEnd.MQNAME, this, AuditTrigger.UPDATE);
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("acab1efb-2b57-40ed-a24c-4f38a2344075")
    @Override
    public IControl getCreationControl(MObject element) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("a5f7a5f1-5727-468c-875e-d1d05561d02c")
    @Override
    public IControl getMoveControl(IElementMovedEvent moveEvent) {
        return null;
    }

    /**
     * Default implementation is using a unique instance for the checker. An alternative implementation consists in creating a new instance of the checker for each element to check. This allows for fine tuning of the check depending on the element status or on some external conditions. Use the 'new instance' strategy only if fine tuning of the check is required for each element, because this strategy creates many objects (performance issues).
     */
    @objid ("3e3836b3-b985-40e5-967f-7d244a61f7a0")
    @Override
    public IControl getUpdateControl(MObject element) {
        return this.checkerInstance;
    }

    /**
     * Default constructor for R1910
     */
    @objid ("0464325f-1e52-46b8-85a3-4e86750342f9")
    public R1910() {
        this.checkerInstance = new CheckR1910(this);
    }

    @objid ("176394f6-058a-446e-9af3-0c1c9efe138e")
    private static class CheckR1910 extends AbstractControl {
        @objid ("87c8d687-b53b-462d-9ed8-dc9b6d0bca21")
        public CheckR1910(IRule rule) {
            super(rule);
        }

        @objid ("5269bf4f-f878-4efe-bc05-fc846b884b6d")
        @Override
        public IDiagnosticCollector doRun(IDiagnosticCollector diagnostic, MObject element) {
            if (element instanceof Link) {
                diagnostic.addEntry(checkR1910((Link) element));
            } else if (element instanceof LinkEnd) {
                diagnostic.addEntry(checkR1910(((LinkEnd) element).getLink()));
            } else if (element instanceof Association) {
                Association assoc = (Association) element;
                for (Link link : assoc.getOccurence()) {
                    diagnostic.addEntry(checkR1910(link));
                }
            } else if (element instanceof AssociationEnd) {
                Association assoc = ((AssociationEnd) element).getAssociation();
                for (Link link : assoc.getOccurence()) {
                    diagnostic.addEntry(checkR1910(link));
                }
            } else {
                UmlUi.LOG.warning("R1910: unsupported element type '%s'", element.getMClass().getName());
            }
            return diagnostic;
        }

        @objid ("7effd56f-20a3-4ee7-af56-33168fdff9c9")
        private IAuditEntry checkR1910(final Link link) {
            AuditEntry auditEntry = new AuditEntry(this.rule.getRuleId(),
                    AuditSeverity.AuditSuccess,
                    link,
                    null);
            
            // The link should have a name to begin with...
            if (link.getName().isEmpty()) {
                return auditEntry;
            }
            
            // This rule only apply if the link instantiate an association
            Association assoc = link.getModel();
            
            if (assoc == null) {
                return auditEntry;
            }
            
            // Then we need to check if the linked instances instantiate the right NameSpace, ie. the NameSpaces linked by the association.
            
            List<LinkEnd> linkEnds = link.getLinkEnd();
            List<AssociationEnd> assocEnds = assoc.getEnd();
            
            Map<LinkEnd, AssociationEnd> mapping = new HashMap<>();
            
            for (LinkEnd le : linkEnds) {
                for (AssociationEnd ae : assocEnds) {
                    Classifier ownerAe = ae.getSource() != null ? ae.getSource() : ae.getOpposite().getTarget();
                    Instance ownerLe = le.getSource() != null ? le.getSource() : le.getOpposite().getTarget();
            
                    if (ownerAe.equals(ownerLe.getBase())) {
                        mapping.put(le, ae);
                    }
                }
            }
            
            if (!(linkEnds.size() == assocEnds.size() && assocEnds.size() == mapping.size())) {
            
                // At this point the rule failed
                // Instances are inconsistent with the Classifiers linked by the association
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(link);
                linkedObjects.add(assoc);
                linkedObjects.addAll(linkEnds);
                linkedObjects.addAll(assocEnds);
                auditEntry.setLinkedInfos(linkedObjects);
            
                return auditEntry;
            }
            
            // We need to check if the name, roles, and properties are the same.
            
            String assocName = assoc.getName();
            String linkName = link.getName();
            
            if (assocName != null && linkName != null && !assocName.equals(linkName)) {
            
                // Rule failed, names do not match
            
                auditEntry.setSeverity(this.rule.getSeverity());
                List<Object> linkedObjects = new ArrayList<>();
                linkedObjects.add(link);
                linkedObjects.add(assoc);
                auditEntry.setLinkedInfos(linkedObjects);
            
                return auditEntry;
            }
            
            for (Entry<LinkEnd, AssociationEnd> entry : mapping.entrySet()) {
            
                LinkEnd linkEnd = entry.getKey();
                AssociationEnd assocEnd = entry.getValue();
            
                String assocEndName = assocEnd.getName();
                String linkEndName = linkEnd.getName();
            
                if ((assocEndName != null &&
                        linkEndName != null &&
                        !(assocEndName.equals(linkEndName)) ||
                        (assocEnd.getSource() != null) != (linkEnd.getSource() != null) ||
                        assocEnd.isIsOrdered() != linkEnd.isIsOrdered() || assocEnd.isIsUnique() != linkEnd.isIsUnique())) {
            
                    // Rule failed
            
                    auditEntry.setSeverity(this.rule.getSeverity());
                    List<Object> linkedObjects = new ArrayList<>();
                    linkedObjects.add(linkEnd);
                    linkedObjects.add(assocEnd);
                    auditEntry.setLinkedInfos(linkedObjects);
            
                    return auditEntry;
                }
            }
            return auditEntry;
        }

    }

}
