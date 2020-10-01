/* 
 * Copyright 2013-2020 Modeliosoft
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

package org.modelio.platform.model.ui.cert;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.security.auth.x500.X500Principal;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.nebula.widgets.pgroup.PGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ExpandEvent;
import org.eclipse.swt.events.ExpandListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.modelio.platform.model.ui.swt.StyledTextHelper;
import org.modelio.platform.ui.dialog.ModelioDialog;
import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

/**
 * Dialog that display invalid certificates errors.
 * <p>
 * The dialog makes an analysis of the error, the certificate, displays the stack trace. It allows the user to either:
 * <ul>
 * <li>cancel the access,
 * <li>trust the certificate for Modelio's runtime lifetime
 * <li>trust the certificate definitively
 * </ul>
 */
@objid ("6785e103-2c70-4e3a-bb8b-3b381ce8c7c7")
public class UntrustedServerDialog extends ModelioDialog {
    /**
     * Code returned by {@link #open()} to trust definitively the certificate.
     */
    @objid ("bcda842e-0976-4c3c-8930-a0765fba0754")
    public static final int TRUST_ALWAYS_ID = 21;

    /**
     * Code returned by {@link #open()} to trust the certificate for the running session.
     */
    @objid ("355b5bd6-560d-4c61-9270-653469afb341")
    public static final int TRUST_ONCE_ID = 20;

    @objid ("9d5e0176-18d8-4628-a7e9-4cbe524bd983")
    private Throwable error;

    @objid ("c4ac3dab-1dea-4498-9b26-78e9f174306b")
    private URI uri;

    @objid ("cab86222-4f98-4ccd-96c6-dc4de6a171b7")
    private X509Certificate[] chain;

    /**
     * @param parentShell a SWT shell
     * @param uri the accessed URI
     * @param chain the invalid certificate chain
     * @param error the error
     */
    @objid ("ade4fc4d-e4ae-47f7-9b7d-498fdab65c9f")
    public UntrustedServerDialog(Shell parentShell, URI uri, X509Certificate[] chain, Throwable error) {
        super(parentShell);
        this.error = error;
        this.uri = uri;
        this.chain = chain;
        
        // FANO 0014264: [SaaS][Linux] At first launch, the 'Unsecured connection' window appears behind the splash screen
        // Copy parent shell SWT.ON_TOP state
        setShellStyle(getShellStyle() | (getParentShell().getStyle() & (SWT.ON_TOP)));
    }

    @objid ("af3961dc-a96e-465d-926f-70ee31ef257c")
    @Override
    public Control createContentArea(Composite parent) {
        final Composite top = new Composite(parent, SWT.BORDER);
        top.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        
        top.setLayout(
                new SectionContainerLayout());
        
        GridLayout topLayout = new GridLayout(1, true);
        topLayout.verticalSpacing = 10;
        topLayout.marginWidth = 4;
        
        //top.setLayout(topLayout);
        GridDataFactory gdFact = GridDataFactory.fillDefaults().grab(true, true);
        
        
        ExpandListener expandListener = new ExpandListener() {
            @Override
            public void itemExpanded(ExpandEvent e) {
                PGroup pGroup = ((PGroup) e.widget);
                pGroup.getParent().layout(true, true);
            }
        
            @Override
            public void itemCollapsed(ExpandEvent e) {
                PGroup pGroup = ((PGroup) e.widget);
                pGroup.getParent().layout(true, true);
            }
        };
        
        StyledText ft = new StyledText(top, SWT.MULTI | SWT.WRAP);
        ft.setEditable(false);
        ft.setEditable(false);
        ft.setBackground(parent.getBackground());
        StyledTextHelper.setStyledText(CoreUi.I18N.getMessage("UntrustedServerDialog.message", this.uri.getHost()), ft);
        gdFact.applyTo(ft);
        
        // Technical error details
        PGroup techArea = new PGroup(top, SWT.NONE);
        techArea.setExpanded(true);
        
        techArea.setLayout(new GridLayout());
        techArea.setText(CoreUi.I18N.getMessage("UntrustedServerDialog.techarea.title"));
        techArea.addExpandListener(expandListener);
        gdFact.applyTo(techArea);
        
        Text widget = new Text(techArea, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        widget.setEditable(false);
        widget.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_DARK_RED));
        widget.setText(buildTechDetails());
        widget.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        
        // Exception stack trace
        PGroup excArea = new PGroup(top, SWT.NONE);
        excArea.setExpanded(false);
        
        excArea.setLayout(new FillLayout());
        excArea.setText(CoreUi.I18N.getMessage("UntrustedServerDialog.exceptionarea.title"));
        excArea.addExpandListener(expandListener);
        gdFact.applyTo(excArea);
        
        StringWriter s = new StringWriter();
        this.error.printStackTrace(new PrintWriter(s));
        widget = new Text(excArea, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        widget.setText(s.toString());
        widget.setEditable(false);
        
        // Certificate area
        PGroup certArea = new PGroup(top, SWT.NONE);
        certArea.setExpanded(false);
        
        certArea.setLayout(new FillLayout());
        certArea.setText(CoreUi.I18N.getMessage("UntrustedServerDialog.certarea.title"));
        certArea.addExpandListener(expandListener);
        gdFact.applyTo(certArea);
        
        widget = new Text(certArea, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        widget.setEditable(false);
        widget.setText(this.chain[0].toString());
        
        // Understand area
        Composite uSection = createUnderstandSection(top, this.uri.getHost());
        ((PGroup)uSection).addExpandListener(expandListener);
        gdFact.applyTo(uSection);
        return top;
    }

    @objid ("6fa7cb53-6b03-4f69-92e1-b18829755434")
    private Composite createUnderstandSection(Composite parent, String serverName) {
        PGroup understandArea = new PGroup(parent, SWT.NONE);
        
        understandArea.setLayout(new FillLayout());
        understandArea.setText(CoreUi.I18N.getMessage("UntrustedServerDialog.understandarea.title"));
        
        Composite compo = new Composite(understandArea, SWT.NONE);
        compo.setLayout(new GridLayout(2, false));
        
        StyledText ft = new StyledText(compo, SWT.MULTI | SWT.WRAP );
        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd.horizontalSpan = 2;
        ft.setLayoutData(gd);
        ft.setBackground(parent.getBackground());
        
        StyledTextHelper.setStyledText(CoreUi.I18N.getMessage("UntrustedServerDialog.understandarea.message", serverName), ft);
        
        Composite toolbar = new Composite(compo, SWT.NONE);
        toolbar.setLayout(new RowLayout());
        toolbar.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, false));
        
        Button button1 = new Button(toolbar, SWT.PUSH);
        button1.setText(CoreUi.I18N.getMessage("UntrustedServerDialog.button.TrustOnce"));
        button1.addSelectionListener(widgetSelectedAdapter(event -> buttonPressed(UntrustedServerDialog.TRUST_ONCE_ID)));
        
        Button button2 = new Button(toolbar, SWT.PUSH);
        button2.setText(CoreUi.I18N.getMessage("UntrustedServerDialog.button.TrustAlways"));
        button2.addSelectionListener(widgetSelectedAdapter(event -> buttonPressed(UntrustedServerDialog.TRUST_ALWAYS_ID)));
        return understandArea;
    }

    @objid ("22bb1caa-15ca-49fb-a15b-ff238c06654e")
    private String buildTechDetails() {
        StringBuilder s = new StringBuilder();
        s.append(this.error.getLocalizedMessage());
        
        s.append("\n\n");
        s.append(CoreUi.I18N.getMessage("UntrustedServerDialog.diag.title"));
        s.append("\n");
        String certFor = this.uri.getHost();
        for (int i = 0; i < this.chain.length; i++) {
            X509Certificate cert = this.chain[i];
            X509Certificate nextCert = i + 1 < this.chain.length ? this.chain[i + 1] : null;
        
            String subjectName = getName(cert.getSubjectX500Principal());
        
            s.append(" * ");
            s.append(CoreUi.I18N.getMessage("UntrustedServerDialog.diag.title.cert", subjectName));
            s.append("\n");
        
            for (String entry : getCertificateDiagnostic(cert, certFor, nextCert)) {
                s.append("    - ");
                s.append(entry);
                s.append("\n");
            }
        
            certFor = subjectName;
        }
        return s.toString();
    }

    @objid ("1c34d5fd-00c6-4131-91ee-8a90ec82462c")
    @Override
    public void addButtonsInButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, true);
    }

    @objid ("5fc7adfc-1205-4923-9005-9948cb4c5daa")
    @Override
    protected void buttonPressed(int buttonId) {
        switch (buttonId) {
        case TRUST_ONCE_ID:
        case TRUST_ALWAYS_ID:
        case IDialogConstants.CANCEL_ID:
            setReturnCode(buttonId);
            close();
            break;
        default:
            super.buttonPressed(buttonId);
        }
    }

    @objid ("3fdadaa1-2b09-4ede-ac0a-9361a887c363")
    @Override
    public void init() {
        getShell().setText(CoreUi.I18N.getMessage("UntrustedServerDialog.title"));
        setTitle(CoreUi.I18N.getString("UntrustedServerDialog.header"));
        setMessage(this.uri.getHost());
    }

    @objid ("b857902a-c6a1-4352-a817-c54257455fe8")
    private static Collection<String> getCertificateDiagnostic(X509Certificate cert, String realHostName, X509Certificate nextCert) {
        Collection<String> ret = new ArrayList<>();
        
        if (nextCert != null) {
            try {
                cert.verify(nextCert.getPublicKey());
                ret.add(CoreUi.I18N.getMessage("UntrustedServerDialog.diag.selfsigned"));
            } catch (InvalidKeyException | CertificateException | NoSuchAlgorithmException | NoSuchProviderException
                    | SignatureException e1) {
                ret.add(e1.getLocalizedMessage());
            }
        } else {
            // Test if the certificate is self signed
            try {
                cert.verify(cert.getPublicKey());
                ret.add(CoreUi.I18N.getMessage("UntrustedServerDialog.diag.selfsigned"));
            } catch (@SuppressWarnings ("unused") SignatureException e1) {
                // Not self signed
                final X500Principal issuer = cert.getIssuerX500Principal();
                if (issuer == null) {
                    ret.add(CoreUi.I18N.getMessage("UntrustedServerDialog.diag.noissuer"));
                } else {
                    ret.add(CoreUi.I18N.getMessage("UntrustedServerDialog.diag.noissuercertificate", getName(issuer)));
                }
            } catch (InvalidKeyException | CertificateException | NoSuchAlgorithmException | NoSuchProviderException e1) {
                // ignore
                ret.add(e1.getLocalizedMessage());
            }
        }
        
        Date time = new Date(System.currentTimeMillis());
        if (time.before(cert.getNotBefore())) {
            // The certificate is not yet valid.
        
            ret.add(CoreUi.I18N.getMessage("UntrustedServerDialog.diag.notyetvalid", DateFormat.getDateTimeInstance().format(cert.getNotBefore())));
        }
        if (time.after(cert.getNotAfter())) {
            // The certificate has expired.
            ret.add(CoreUi.I18N.getMessage("UntrustedServerDialog.diag.expired", DateFormat.getDateTimeInstance().format(cert.getNotAfter())));
        }
        String certHostName = getName(cert.getSubjectX500Principal());
        
        if (!realHostName.equals(certHostName)) {
            try {
                Collection<List<?>> altNames = cert.getSubjectAlternativeNames();
                if (altNames != null) {
                    for (Object nameList : altNames) {
                        if (nameList instanceof Collection && ((Collection<?>) nameList).size() >= 2) {
                            Object[] name = ((Collection<?>) nameList).toArray();
                            Object type = name[0];
                            Object host = name[1];
                            if (type instanceof Integer && host instanceof String) {
                                if (((Integer) type).intValue() == 2 && host.equals(realHostName)) {
                                    // The certificate hostname does match
                                    return ret;
                                }
                            }
                        }
                    }
                }
            } catch (@SuppressWarnings ("unused") CertificateParsingException e) {
                // ignore
            }
            // The certificate hostname does not match
            ret.add(CoreUi.I18N.getMessage("UntrustedServerDialog.diag.badhostname"));
        }
        return ret;
    }

    @objid ("89555707-2268-4175-ab19-cfbb212c0f98")
    private static String getName(X500Principal pr) {
        String dn = pr.getName();
        LdapName ldapDN;
        try {
            ldapDN = new LdapName(dn);
            for (Rdn r : ldapDN.getRdns()) {
                final Attribute attribute = r.toAttributes().get("cn");
                if (attribute != null) {
                    return (String) attribute.get();
                }
            }
            return dn;
        } catch (NamingException e) {
            return e.toString() + "( from " + pr.toString() + ")";
        }
    }

    @objid ("3fe1a65c-7a87-4a90-9400-51f34dc5e18b")
    @Override
    protected Point getInitialSize() {
        return new Point(600, 800);
    }

    /**
     * Layout to be used on a Composite composed of sections on one column .
     * <p>
     * This is a replacement for GridLayout and RowLayout that renders
     * badly in our case.
     * <p>
     * This layout distribute bits of free height evenly to each section
     * until their height request is fulfilled or there is no left height.
     */
    @objid ("07921cf5-3969-4bee-81db-371bc1b6b2db")
    private static final class SectionContainerLayout extends Layout {
        @objid ("0b1f224d-35a0-4278-b83e-a72931293657")
        private int margin = 4;

        @objid ("e4c9e9aa-8aad-4282-91ac-b6fb0d6f457d")
        private int vSpacing = 10;

        @objid ("67ca6299-fc36-4de1-9f8d-5670e47e9008")
        @Override
        protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
            int childWHint = wHint == SWT.DEFAULT ? SWT.DEFAULT : wHint - this.margin * 2 ;
            if (childWHint < 0)
                childWHint = SWT.DEFAULT;
            
            Point ret = new Point(this.margin * 2,this.margin * 2);
            for (Control child : composite.getChildren()) {
                Point r = child.computeSize(childWHint, SWT.DEFAULT);
                ret.x = Math.max(r.x, ret.x);
                ret.y += r.y + this.vSpacing;
            }
            
            ret.y -= this.vSpacing;
            
            if (hHint > 0 && ret.y > hHint)
                ret.y = hHint;
            if (wHint > 0 && ret.x > wHint)
                ret.x = wHint;
            return ret;
            //return composite.getSize();
        }

        @objid ("68a45e70-9713-4a44-9504-d293670b61d1")
        @Override
        protected void layout(Composite composite, boolean flushCache) {
            Control[] children = composite.getChildren();
            int nChildren = children.length;
            
            Rectangle bounds = composite.getClientArea();
            Rectangle area = new Rectangle(bounds.x + this.margin, bounds.y + this.margin, bounds.width-2*this.margin, bounds.height-2*this.margin);
            
            int x = area.x;
            int y = area.y;
            int w = area.width;
            int wHint = area.width;
            
            int hTheoritical = (area.height - (nChildren-1)*this.vSpacing) / nChildren;
            
            int spareHeight = 0;
            int spareTargetsNb = nChildren;
            
            Point[] childReqSizes = new Point[children.length];
            int [] newHeights = new int[children.length];
            for (int i = 0; i < children.length; i++) {
                Control child = children[i];
                Point r = child.computeSize(wHint, SWT.DEFAULT);
                childReqSizes[i] = r;
            
                if (r.y < hTheoritical) {
                    newHeights[i] = r.y;
                    spareHeight += (hTheoritical - r.y);
                    spareTargetsNb--;
                } else {
                    newHeights[i] = hTheoritical;
                }
            }
            
            if (true) {
            
                // distribute evenly spare height among hungry widget
                int newMeanH = hTheoritical;
                for (int step = 0; (step <5 && spareHeight>0 && spareTargetsNb>0); step++) {
            
                    int hIncs = (spareHeight / spareTargetsNb);
                    newMeanH += hIncs ;
                    for (int i = 0; i < children.length; i++) {
                        int reqH = childReqSizes[i].y;
                        if (reqH > newHeights[i]) {
                            int newH = Math.min(newMeanH, reqH);
                            int hInc = newH - newHeights[i];
                            spareHeight -= hInc;
                            newHeights[i]  = newH;
                            if (newHeights[i] >= reqH) {
                                spareTargetsNb--;
                            }
                        }
                    }
                }
            
                // Set bounds
                for (int i = 0; i < children.length; i++) {
                    Control child = children[i];
                    Rectangle b = new Rectangle(x, y, w, newHeights[i]);
                    child.setBounds(b);
                    y += b.height + this.vSpacing;
                }
            } else  {
                int h = hTheoritical + ((spareTargetsNb != 0) ? spareHeight / spareTargetsNb : 0);
                for (int i = 0; i < children.length; i++) {
                    Control child = children[i];
                    Point r = childReqSizes[i];
                    Rectangle b = new Rectangle(x, y, w, Math.min(h, r.y));
                    child.setBounds(b);
                    y += b.height + this.vSpacing;
                }
            }
        }

    }

}
