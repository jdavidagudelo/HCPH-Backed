<?xml version="1.0"?>
<!-- edited with XMLSPY v2004 rel. 2 U (http://www.xmlspy.com) by mc (mw) -->
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:n3="http://www.w3.org/1999/xhtml"
	xmlns:hl7="urn:hl7-org:v3" xmlns:n2="urn:hl7-org:v3/meta/voc"
	xmlns:voc="urn:hl7-org:v3/voc" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:output method="html" indent="yes" version="4.01"
		encoding="ISO-8859-1" doctype-public="-//W3C//DTD HTML 4.01//EN" />
	<!-- CDA document -->
	<xsl:variable name="tableWidth">
		50%
	</xsl:variable>
	<xsl:variable name="title">
		<xsl:choose>
			<xsl:when test="/hl7:ClinicalDocument/hl7:title">
				<xsl:value-of select="/hl7:ClinicalDocument/hl7:title" />
			</xsl:when>
			<xsl:otherwise>
				Antecedentes de Alergias, medicamentos y patologías
			</xsl:otherwise>
		</xsl:choose>
	</xsl:variable>
	<xsl:template match="/">
		<xsl:apply-templates select="hl7:ClinicalDocument" />
	</xsl:template>
	<xsl:template match="hl7:ClinicalDocument">
		<html>
			<head>
				<!-- <meta name='Generator' content='&CDA-Stylesheet;'/> -->
				<xsl:comment>
				</xsl:comment>
				<title>
					<xsl:value-of select="$title" />
				</title>
				<xsl:comment>
					General CSS
				</xsl:comment>
				<link type="text/css" rel="stylesheet" href="../TPH.css"></link>
				<style type="text/css" media="screen">
					
					ul li {
					color: black;
					background:
					url(../images/square.png) no-repeat 7px 7px transparent;
					list-style-type: none;
					margin: 0;
					padding: 0px 0px 1px 24px;
					vertical-align: middle;
					}

				</style>
			</head>
			<body>
				<h2 align="center">
					<xsl:value-of select="$title" />
				</h2>
				<br />
				<xsl:apply-templates select="hl7:component/hl7:structuredBody" />
				<!--<xsl:call-template name="bottomline" /> -->
			</body>
		</html>
	</xsl:template>
	<!-- Get a Name -->
	<xsl:template name="getName">
		<xsl:param name="name" />
		<xsl:choose>
			<xsl:when test="$name/hl7:family">
				<xsl:if test="$name/hl7:prefix">
					<xsl:value-of select="$name/hl7:prefix" />
					<xsl:text> </xsl:text>
				</xsl:if>
				<xsl:value-of select="$name/hl7:given" />
				<xsl:text> </xsl:text>
				<xsl:value-of select="$name/hl7:family" />
				<xsl:text> </xsl:text>
				<xsl:if test="$name/hl7:suffix">
					<xsl:text>, </xsl:text>
					<xsl:value-of select="$name/hl7:suffix" />
				</xsl:if>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$name" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- Format Date outputs a date in Month Day, Year form e.g., 19991207 ==> 
		December 07, 1999 -->
	<xsl:template name="formatDate">
		<xsl:param name="date" />
		<xsl:choose>
			<xsl:when test='substring ($date, 7, 1)="0"'>
				<xsl:value-of select="substring ($date, 8, 1)" />
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="substring ($date, 7, 2)" />
			</xsl:otherwise>
		</xsl:choose>
		<xsl:text> de </xsl:text>
		<xsl:variable name="month" select="substring ($date, 5, 2)" />
		<xsl:choose>
			<xsl:when test="$month='01'">
				<xsl:text>Enero </xsl:text>
			</xsl:when>
			<xsl:when test="$month='02'">
				<xsl:text>Febrero</xsl:text>
			</xsl:when>
			<xsl:when test="$month='03'">
				<xsl:text>Marzo </xsl:text>
			</xsl:when>
			<xsl:when test="$month='04'">
				<xsl:text>Abril </xsl:text>
			</xsl:when>
			<xsl:when test="$month='05'">
				<xsl:text>Mayo </xsl:text>
			</xsl:when>
			<xsl:when test="$month='06'">
				<xsl:text>Junio </xsl:text>
			</xsl:when>
			<xsl:when test="$month='07'">
				<xsl:text>Julio </xsl:text>
			</xsl:when>
			<xsl:when test="$month='08'">
				<xsl:text>Agosto </xsl:text>
			</xsl:when>
			<xsl:when test="$month='09'">
				<xsl:text>Septiembre </xsl:text>
			</xsl:when>
			<xsl:when test="$month='10'">
				<xsl:text>Octubre </xsl:text>
			</xsl:when>
			<xsl:when test="$month='11'">
				<xsl:text>Noviembre </xsl:text>
			</xsl:when>
			<xsl:when test="$month='12'">
				<xsl:text>Diciembre </xsl:text>
			</xsl:when>
		</xsl:choose>
		<xsl:text> de </xsl:text>
		<xsl:value-of select="substring ($date, 1, 4)" />
	</xsl:template>
	<!-- StructuredBody -->
	<xsl:template match="hl7:component/hl7:structuredBody">
		<xsl:apply-templates select="hl7:component/hl7:section" />
	</xsl:template>
	<!-- Component/Section -->
	<xsl:template match="hl7:component/hl7:section">
		<xsl:apply-templates select="hl7:title" />
		<xsl:apply-templates select="hl7:text" />
		<xsl:apply-templates select="hl7:component/hl7:section" />
	</xsl:template>
	<!-- Title -->
	<xsl:template match="hl7:title">
		<h3>
			<span style="font-weight:bold;">
				<xsl:value-of select="." />
			</span>
		</h3>
	</xsl:template>
	<!-- Text -->
	<xsl:template match="hl7:text">
		<xsl:apply-templates select="hl7:htmlLink" />
		<xsl:apply-templates />
	</xsl:template>
	<!-- paragraph -->
	<xsl:template match="hl7:paragraph">
		<xsl:apply-templates />
		<br />
	</xsl:template>
	<!-- Content w/ deleted text is hidden -->
	<xsl:template match="hl7:content[@revised='delete']" />
	<!-- content -->
	<xsl:template match="hl7:content">
		<xsl:apply-templates />
	</xsl:template>
	<!-- list -->
	<xsl:template match="hl7:list">
		<xsl:if test="hl7:caption">
			<span style="font-weight:bold; ">
				<xsl:apply-templates select="hl7:caption" />
			</span>
		</xsl:if>
		<ul>
			<xsl:for-each select="hl7:item">
				<li>
					<xsl:apply-templates />
				</li>
			</xsl:for-each>
		</ul>
	</xsl:template>
	<xsl:template match="hl7:list[@listType='ordered']">
		<xsl:if test="hl7:caption">
			<span style="font-weight:bold; ">
				<xsl:apply-templates select="hl7:caption" />
			</span>
		</xsl:if>
		<ol>
			<xsl:for-each select="hl7:item">
				<li>
					<xsl:apply-templates />
				</li>
			</xsl:for-each>
		</ol>
	</xsl:template>
	<!-- caption -->
	<xsl:template match="hl7:caption">
		<xsl:apply-templates />
		<xsl:text>: </xsl:text>
	</xsl:template>
	<!-- Tables -->
	<xsl:template
		match="hl7:table/@*|hl7:thead/@*|hl7:tfoot/@*|hl7:tbody/@*|hl7:colgroup/@*|hl7:col/@*|hl7:tr/@*|hl7:th/@*|hl7:td/@*">
		<xsl:copy>
			<xsl:apply-templates />
		</xsl:copy>
	</xsl:template>
	<xsl:template match="hl7:table">
		<table>
			<xsl:apply-templates />
		</table>
	</xsl:template>
	<xsl:template match="hl7:thead">
		<thead>
			<xsl:apply-templates />
		</thead>
	</xsl:template>
	<xsl:template match="hl7:tfoot">
		<tfoot>
			<xsl:apply-templates />
		</tfoot>
	</xsl:template>
	<xsl:template match="hl7:tbody">
		<tbody>
			<xsl:apply-templates />
		</tbody>
	</xsl:template>
	<xsl:template match="hl7:colgroup">
		<colgroup>
			<xsl:apply-templates />
		</colgroup>
	</xsl:template>
	<xsl:template match="hl7:col">
		<col>
			<xsl:apply-templates />
		</col>
	</xsl:template>
	<xsl:template match="hl7:tr">
		<tr>
			<xsl:apply-templates />
		</tr>
	</xsl:template>
	<xsl:template match="hl7:th">
		<th>
			<xsl:apply-templates />
		</th>
	</xsl:template>
	<xsl:template match="hl7:td">
		<td>
			<xsl:apply-templates />
		</td>
	</xsl:template>
	<xsl:template match="hl7:table/hl7:caption">
		<span style="font-weight:bold; ">
			<xsl:apply-templates />
		</span>
	</xsl:template>
	<!-- RenderMultiMedia this currently only handles GIF's and JPEG's. It could, 
		however, be extended by including other image MIME types in the predicate 
		and/or by generating <object> or <applet> tag with the correct params depending 
		on the media type @ID =$imageRef referencedObject -->
	<xsl:template match="hl7:renderMultiMedia">
		<xsl:variable name="imageRef" select="@referencedObject" />
		<xsl:choose>
			<xsl:when test="//hl7:regionOfInterest[@ID=$imageRef]">
				<!-- Here is where the Region of Interest image referencing goes -->
				<xsl:if
					test='//hl7:regionOfInterest[@ID=$imageRef]//hl7:observationMedia/hl7:value[@mediaType="image/gif" or @mediaType="image/jpeg"]'>
					<br clear="all" />
					<xsl:element name="img">
						<xsl:attribute name="src">http://www.hospitalitaliano.org.ar/intranet/
				<xsl:value-of
							select="//hl7:regionOfInterest[@ID=$imageRef]//hl7:observationMedia/hl7:value/hl7:reference/@value" /></xsl:attribute>
					</xsl:element>
				</xsl:if>
			</xsl:when>
			<xsl:otherwise>
				<!-- Here is where the direct MultiMedia image referencing goes -->
				<xsl:if
					test='//hl7:observationMedia[@ID=$imageRef]/hl7:value[@mediaType="image/gif" or @mediaType="image/jpeg"]'>
					<br clear="all" />
					<xsl:element name="img">
						<xsl:attribute name="src">http://www.hospitalitaliano.org.ar/intranet/
				<xsl:value-of
							select="//hl7:observationMedia[@ID=$imageRef]/hl7:value/hl7:reference/@value" /></xsl:attribute>
					</xsl:element>
				</xsl:if>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- Stylecode processing Supports Bold, Underline and Italics display -->
	<xsl:template match="//hl7:*[@styleCode]">
		<xsl:if test="@styleCode='Bold'">
			<xsl:element name="b">
				<xsl:apply-templates />
			</xsl:element>
		</xsl:if>
		<xsl:if test="@styleCode='Italics'">
			<xsl:element name="i">
				<xsl:apply-templates />
			</xsl:element>
		</xsl:if>
		<xsl:if test="@styleCode='Underline'">
			<xsl:element name="u">
				<xsl:apply-templates />
			</xsl:element>
		</xsl:if>
		<xsl:if
			test="contains(@styleCode,'Bold') and contains(@styleCode,'Italics') and not (contains(@styleCode, 'Underline'))">
			<xsl:element name="b">
				<xsl:element name="i">
					<xsl:apply-templates />
				</xsl:element>
			</xsl:element>
		</xsl:if>
		<xsl:if
			test="contains(@styleCode,'Bold') and contains(@styleCode,'Underline') and not (contains(@styleCode, 'Italics'))">
			<xsl:element name="b">
				<xsl:element name="u">
					<xsl:apply-templates />
				</xsl:element>
			</xsl:element>
		</xsl:if>
		<xsl:if
			test="contains(@styleCode,'Italics') and contains(@styleCode,'Underline') and not (contains(@styleCode, 'Bold'))">
			<xsl:element name="i">
				<xsl:element name="u">
					<xsl:apply-templates />
				</xsl:element>
			</xsl:element>
		</xsl:if>
		<xsl:if
			test="contains(@styleCode,'Italics') and contains(@styleCode,'Underline') and contains(@styleCode, 'Bold')">
			<xsl:element name="b">
				<xsl:element name="i">
					<xsl:element name="u">
						<xsl:apply-templates />
					</xsl:element>
				</xsl:element>
			</xsl:element>
		</xsl:if>
	</xsl:template>
	<!-- Superscript or Subscript -->
	<xsl:template match="hl7:sup">
		<xsl:element name="sup">
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>
	<xsl:template match="hl7:sub">
		<xsl:element name="sub">
			<xsl:apply-templates />
		</xsl:element>
	</xsl:template>
	<!-- Bottomline -->
	<xsl:template name="bottomline">
		<b>
			<xsl:text>Firmado por: </xsl:text>
		</b>
		<xsl:call-template name="getName">
			<xsl:with-param name="name"
				select="/hl7:ClinicalDocument/hl7:legalAuthenticator/hl7:assignedEntity/hl7:assignedPerson/hl7:name" />
		</xsl:call-template>
		<xsl:text>.  fecha:  </xsl:text>
		<xsl:call-template name="formatDate">
			<xsl:with-param name="date"
				select="//hl7:ClinicalDocument/hl7:legalAuthenticator/hl7:time/@value" />
		</xsl:call-template>
	</xsl:template>
	<xsl:template match="hl7:htmlLink">
		<xsl:element name="a">
			<xsl:attribute name="href"><xsl:value-of select="./@href" /></xsl:attribute>
			<xsl:value-of select="." />
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
