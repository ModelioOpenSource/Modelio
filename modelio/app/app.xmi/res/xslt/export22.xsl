<?xml version="1.0"?>
<xsl:stylesheet version="1.0" 
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
		xmlns:xmi="http://schema.omg.org/spec/XMI/2.1" 
		xmlns:uml="http://www.eclipse.org/uml2/2.1.0/UML"  
		xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore" 
		xmlns:sysML="http://www.topcased.org/2.0/sysML"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
		
        
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<!-- Reproduit tous les noeuds du document  -->

<xsl:template match="/">
  <xsl:apply-templates />
</xsl:template>


<xsl:template match=" * | @*  | text() ">	
	<xsl:copy>
		<xsl:apply-templates select="@* | * | text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//appliedProfile[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Profile</xsl:attribute>
    	<xsl:attribute name="applyingPackage" namespace="http://schema.omg.org/spec/XMI/2.1"><xsl:value-of select="../@xmi:id" /></xsl:attribute>
    <xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//profileApplication[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:ProfileApplication</xsl:attribute>
		<xsl:attribute name="applyingPackage" namespace="http://schema.omg.org/spec/XMI/2.1"><xsl:value-of select="../@xmi:id" /></xsl:attribute>
      	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//slot[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Slot</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//generalization[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Generalization</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="/*[name()!='ownedTemplateSignature']/ownedParameter[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Parameter</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//ownedTemplateSignature/ownedParameter[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:TemplateParameter</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//templateBinding[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:TemplateBinding</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//ownedTemplateSignature[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:TemplateSignature</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//transition[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Transition</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//ownedReception[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Reception</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//ownedRule[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Constraint</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>


<xsl:template match="//ownedOperation[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Operation</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//templateBinding[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:TemplateBinding</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//parameterSubstitution[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:TemplateParameterSubstitution</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>


<xsl:template match="//trigger[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Trigger</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//ownedAttribute[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Property</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//ownedEnd[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Property</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>


<xsl:template match="//include[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Include</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>


<xsl:template match="//extend[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Extend</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//ownedComment[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Comment</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//connection[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:ConnectionPointReference</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>


<xsl:template match="//connectionPoint[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Pseudostate</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>


<xsl:template match="//message[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Message</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

<xsl:template match="//ownedLiteral[not(@xmi:type)]">  
	<xsl:copy>
		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:EnumerationLiteral</xsl:attribute>
    	<xsl:apply-templates select="@* |*| text()"/>
	</xsl:copy>
</xsl:template>

</xsl:stylesheet>