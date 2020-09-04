<?xml version="1.0"?>
<xsl:stylesheet version="1.0" 
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
		xmlns:xmi="http://schema.omg.org/spec/XMI/2.1" 
		xmlns:uml="http://www.eclipse.org/uml2/2.1.0/UML"  
		xmlns:ecore="http://www.eclipse.org/emf/2002/Ecore" 
		xmlns:sysML="http://www.topcased.org/2.0/sysML"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
		
        
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

<xsl:template match="/"> 
		<xsl:apply-templates />
</xsl:template>

<!-- Supress nodes under stereotypes--> 
<xsl:template match="//xmi:XMI/*[count(./*) > 0][name()!='uml:Model'][name()!='uml:Profile'][name()!='uml:Package']/*" />  

<xsl:template match="//xmi:XMI/*[count(./*) > 0][name()!='uml:Model'][name()!='uml:Profile'][name()!='uml:Package']">  
	<xsl:copy>
	 	<xsl:for-each select="./*"> 	  
		  		<xsl:attribute name="{name()}"><xsl:value-of select="text()"/></xsl:attribute>			
	  	</xsl:for-each> 
	  	<xsl:apply-templates select="@* | *"/> 
	</xsl:copy>		
</xsl:template>


<!-- delete elements --> 
<xsl:template match="comment()"/>
<xsl:template match="//*[@xmi:type='uml:Model']/group"/>  
<xsl:template match="//*[@xmi:type='uml:Model']/node"/>  
<xsl:template match="//*[@xmi:type='uml:Model']/packagedElement[@xmi:type='uml:CallBehaviorAction']"/> 
<xsl:template match="//*[@xmi:type='uml:Model']/packagedElement[@xmi:type='uml:InitialNode']"/> 
<xsl:template match="//*[@xmi:type='uml:Model']/packagedElement[@xmi:type='uml:CentralBufferNode']"/> 
<xsl:template match="//*[@xmi:type='uml:Model']/packagedElement[@xmi:type='uml:DecisionNode']"/> 
<xsl:template match="//*[@xmi:type='uml:Model']/packagedElement[@xmi:type='uml:ActivityFinalNode']"/> 
<xsl:template match="//*[@xmi:type='uml:Model']/packagedElement[@xmi:type='uml:ExpansionRegion']"/> 
<xsl:template match="//*[@xmi:type='uml:Model']/packagedElement[@xmi:type='uml:Port']"/> 
<xsl:template match="//*[@xmi:type='uml:Model']/packagedElement[@xmi:type='uml:State']"/> 
<xsl:template match="//*[@xmi:type='uml:Model']/packagedElement[@xmi:type='uml:PseudoState']"/> 
<xsl:template match="//*[@xmi:type='uml:Model']/packagedElement[@xmi:type='uml:Pseudostate']"/>
<xsl:template match="//*[@xmi:type='uml:Model']/packagedElement[@xmi:type='uml:FinalState']"/> 
<xsl:template match="//*[@xmi:type='uml:Model']/packagedElement[@xmi:type='uml:InputPin']"/>
<xsl:template match="//*[@xmi:type='uml:Model']/packagedElement[@xmi:type='uml:OutputPin']"/> 
<xsl:template match="//packagedElement[@xmi:type='uml:ObjectFlow']"/>    
<xsl:template match="//packagedElement[@xmi:type='uml:ControlFlow']"/>     
<xsl:template match="//packagedElement[@xmi:type='uml:Transition']"/> 

<xsl:template match="//*[@xmi:type='uml:Node']/nestedClassifier[@xmi:type='uml:InstanceSpecification']"/> 

<!-- Supress obsolete comment  -->
<xsl:template match="//ownedComment[@xmi:annotatedElement='']"/>


<!-- Supress xmi:Documentation node -->
<xsl:template match="//xmi:Documentation"/>  


<!-- Supress uml:Diagram node -->
<xsl:template match="//uml:Diagram"/>  

<!-- Supress inPartition attribute -->
<xsl:template match="//*[@xmi:type='uml:CallBehaviorAction']/@inPartition"/>  
<xsl:template match="//*[@xmi:type='uml:Comment']/@inPartition"/> 
<xsl:template match="//*[@xmi:type='uml:InitialNode']/@inPartition"/> 
<xsl:template match="//ownedRule/@inPartition"/> 


<!-- Supress isSubmachineState attribute  -->
<xsl:template match="//@isSubmachineState"/> 

<!-- Supress definingClassifier attribute in Slot element -->
<xsl:template match="//slot[@xmi:type='uml:Slot']/@definingClassifier"/> 

<!-- Supress nestedArtifact under InstanceSpecification element -->
<xsl:template match="//packagedElement[@xmi:type='uml:InstanceSpecification']/nestedArtifact"/> 


<!-- Clean Entreprise Architect extensions-->
<xsl:template match="//uml:Model">  
	<xsl:copy>	  
  		<xsl:for-each select="//xmi:Extension[@extender='Enterprise Architect']/profiles/*"> 
	    	<xsl:element name="packagedElement">  
				<xsl:attribute name="xmi:id" namespace="http://schema.omg.org/spec/XMI/2.1"><xsl:value-of select="@xmi:id" /></xsl:attribute>
		  		<xsl:attribute name="xmi:type" namespace="http://schema.omg.org/spec/XMI/2.1">uml:Profile</xsl:attribute>
				<xsl:attribute name="name" namespace=""><xsl:value-of select="@name" /></xsl:attribute>
				<xsl:attribute name="xmi:version" namespace="http://schema.omg.org/spec/XMI/2.1"><xsl:value-of select="@xmi:version" /></xsl:attribute>
				<xsl:copy-of select="*"/>
	 		</xsl:element> 
		</xsl:for-each> 
  
  		<xsl:for-each select="//xmi:Extension[@extender='Enterprise Architect']/primitivetypes/*"> 
			<xsl:copy-of select="*"/>			 
    	</xsl:for-each> 
    
		<xsl:apply-templates select="@* | * | text()"/>		   	 
	</xsl:copy>	
</xsl:template>

<xsl:template match="//ownedMember[../@xmi:type='uml:Model']">       
    <xsl:apply-templates select="@* | * | text()"/> 
    <xsl:element name="packagedElement">  
		  <xsl:copy-of select="@*| *| text()"/>    		                                           
	</xsl:element> 
</xsl:template>

<xsl:template match="//ownedMember[../@xmi:type='uml:Package']">   
    <xsl:apply-templates select="@* | * | text()"/> 
    <xsl:element name="packagedElement">  	   
		  <xsl:copy-of select="@*| *| text()"/>               
	</xsl:element> 
</xsl:template>

<!-- Supress reference to Entreprise Architect void type -->
<xsl:template match="//@type[. = 'EAnone_void']"/> 


<!-- Supress idref in type attribute -->
<xsl:template match="//*[./type[@xmi:idref]]">
	<xsl:copy> 
  		<xsl:variable name="ID"><xsl:value-of select="./type/@xmi:idref"/></xsl:variable>
  		<xsl:attribute name="type" namespace="">
  			<xsl:for-each select="//*[@xmi:id=$ID]">
    			<xsl:if test="./@xmi:type != 'uml:InstanceSpecification'">
    				<xsl:value-of select="$ID"/>
    			</xsl:if>
 			</xsl:for-each> 
  		</xsl:attribute> 
 		<xsl:apply-templates select="@* | * | text()"/> 
  	</xsl:copy>
</xsl:template> 

<xsl:template match="//*/type[@xmi:idref]"/> 

<xsl:template match="//*/type[not(@xmi:type)][contains(@href,'pathmap://UML_LIBRARIES/UMLPrimitiveTypes.library.uml')]">
	<xsl:copy>   		
  		<xsl:attribute name="xmi:type" namespace="">uml:PrimitiveType</xsl:attribute> 
  		<xsl:apply-templates select="@* | * | text()"/> 
  	</xsl:copy>
</xsl:template> 

 <!-- Supress defaultValue with no type -->
<xsl:template match="//defaultValue[not((../@xmi:type) or (../@xsi:type) or (../type))]"/>


<!-- Supress action element with is abstract element -->   
<xsl:template match="//*[@xmi:type='uml:Action']"/> 

<!-- Supress property under actor -->   
<xsl:template match="//packagedElement[@xmi:type='uml:Actor']/ownedAttribute[not(@association)]"/> 

<!-- Supress port under instanceSpecification --> 
<xsl:template match="//packagedElement[@xmi:type='uml:InstanceSpecification']/ownedPort"/> 

<!-- Supress property under instanceSpecification --> 
<xsl:template match="//packagedElement[@xmi:type='uml:InstanceSpecification']/ownedAttribute"/> 

<!-- Supress activity under activity --> 
<xsl:template match="//ownedBehavior[@xmi:type='uml:Activity']/packagedElement[@xmi:type='uml:Activity']"/> 



<!-- Reproduce all nodes  -->
<xsl:template match=" * | @*  | text() ">	
	<xsl:copy>
		<xsl:apply-templates select="@* | * | text()"/>
	</xsl:copy>	
</xsl:template> 

</xsl:stylesheet>