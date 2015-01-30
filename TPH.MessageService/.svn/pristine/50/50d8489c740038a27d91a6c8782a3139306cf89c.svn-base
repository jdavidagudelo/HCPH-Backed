<?xml version="1.0" encoding="UTF-8"?>
<!--
  Title: CDA XSL StyleSheet
  Original Filename: cda.xsl 
  Description: this stylesheet is a project specific transformation using CCD like instances. The starting point was the fixed version of cda-singleton.xsl v3.0.
               The differences are just in what headers are displayed at the top of the rendering.
  Version: 3.0
  Revision History: 08/12/08 Jingdong Li updated
  Revision History: 12/11/09 KH updated 
  Revision History:  03/30/10 Jingdong Li updated.
  Revision History:  08/25/10 Jingdong Li updated
  Revision History:  09/17/10 Jingdong Li updated
  Revision History:  01/05/11 Jingdong Li updated
  Revision History:  03/05/11 Alexander Henket updated
  	- Added language file and a template getLocalizedString to deal with it
  	- Added parameter textLang to feed the desired language. Does not work with just a browser, so
  	  textLang needs to be set in any environment
  	- Replaced all current text in the stylesheet with calls to getLocalizedString
  	- Improved layout of page so there's now a fixed header. Shrunk the TOC into a button with hover 
  	  to open so it doesn't always take up all screen space. The layout is enforced using div with an id
  	  To avoid lots of scrolling in the header area this part needed to be predictable and small. All 
  	  header info except recordTarget is now to the bottom of the document
  	  Also the document title was made less big 
  	- Added support for every styleCode in CDA release 2, not just bold/italics/underline, but also all 
  	  list styles and e.g. Botrule
    - Added support for footnotes/footnoteRefs
    - Added support for multiple section authors (used to be just one, and support was bugged)
    - Added support for (multiple) section informants
    - Added support for section subject
  	- Added support for @ID, @IDREF, @language, and all other style properties from CDA release 2
  	- Added support for the display of nonXMLBody with text/plain content
  	- Added support for Narrative block <linkHtml>
  	- Added support for revisions. Deletions will get a strikethrough, Insertions an underline and overline
  	  Both get a title saying 'delete' or 'insert'. This feature is off by default, but above the TOC there's a
  	  toggle to switch revisions marks on or off. A little Javascript was used for that.
    - Added confidentiality to the title of the document if present and other than N (Normal). If someone took 
      the effort to send the confidentiality it should be 'in your face' as recipient.
    - Added support for multiple section authors (used to be just one, and support was bugged)
    - Added support for (multiple) section informants
    - Added support for section subject
    - Added a switch to diable Javascript in case the environment requires that
    - Improved support for data type II when there's only a root and no extension
    - Improved support for the author organization which not displayed
    - Improved support for telecom and addr use codes. Now supports multiples
    - Improved support for names. Now gets out every node, supporting mixed mode too.
  	- Improved readability for the support for lists by combining the two templates
  	- Improved support for observationMedia by removing the limitation of just gif and jpeg. If the browser does 
  	  not support a given type, then at least a question mark is displayed. Before there was nothing
  	- Improved support for observationMedia by adding the id (if available) to the alt and title of the image
    - Added support for inline base64 images. HTML actually can deal with that too
    - Improved handling of addresses so it now walks through all nodes, support mixed content. Also, the elements 
      are now handled in the order of the instance and not just US order
    - Improved support for dates by including a language switch. Now just handles en-US and nl-NL, but could handle more
      Likely more sophisticated support is in order, but this works for our purposes while leaving US conventions intact
    - Improved handling of names, addresses and telecom. The templates gave incorrect results for multiples of these, 
      mixing the contents of the latter with the first. Added name use code to the display of names if available.
    - Fixed the author string in the main document info. It used to '-', but is now "Author" in any supported language
  Revision History:  30/05/11 Alexander Henket updated
    - Participant codes are now displayed by default instead of only when the name is omitted
    - Fixed translation of patientIdLong, by using the literal string instead treating it as a node
    - Improved title on ids in fixed top header by adding a newline between multiple ids
    - Changed behavior of "Waarschuwingen" and "Behandelaanwijzigingen" to display "Er zijn geen ..." if they are not 
      applicable, instead of showing nothing
    - Changed "Behandelaanwijzingen into "Behandelrestricties"
  Revision History:  31/05/11 Alexander Henket updated
  	- Improved show-code to return translated codes, and finally the original code if originalText and @displayName fail
  	- Improved label on bottom participant to include translated assignedEntity/code if available
  	- Improved readability of participant/assignedEntity

  Specification: ANSI/HL7 CDAR2  
  The current version and documentation are available at http://www.lantanagroup.com/resources/tools/. 
  We welcome feedback and contributions to tools@lantanagroup.com
  The stylesheet is the cumulative work of several developers; the most significant prior milestones were the foundation work from HL7 
  Germany and Finland (Tyylitiedosto) and HL7 US (Calvin Beebe), and the presentation approach from Tony Schaller, medshare GmbH provided at IHIC 2009. 
-->
<!-- LICENSE INFORMATION
  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
  You may obtain a copy of the License at  http://www.apache.org/licenses/LICENSE-2.0 
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:n1="urn:hl7-org:v3" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:variable name="textEncoding">iso-8859-1</xsl:variable>
	<xsl:variable name="vocFile" select="'cda_l10n.xml'"/>
	<xsl:variable name="vocMessages" select="document($vocFile)"/>
	<xsl:variable name="textlangDefault" select="'en-US'"/>
	<xsl:variable name="contentHeaderWidth" select="'98%'"/>
	<xsl:variable name="contentMainWidth" select="'100%'"/>
	<xsl:variable name="contentHeaderHeight" select="'150px'"/>
	<xsl:param name="textLang">
		<xsl:choose>
			<xsl:when test="/n1:ClinicalDocument/n1:languageCode/@code">
				<xsl:value-of select="/n1:ClinicalDocument/n1:languageCode/@code"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="'en-US'"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:param>
	<xsl:variable name="textLangLowerCase">
		<xsl:call-template name="caseDown">
			<xsl:with-param name="data" select="$textLang"/>
		</xsl:call-template>
	</xsl:variable>
	<xsl:variable name="textLangPartLowerCase" select="substring($textLangLowerCase,1,2)"/>
	<xsl:variable name="textLangDefaultLowerCase">
		<xsl:call-template name="caseDown">
			<xsl:with-param name="data" select="$textlangDefault"/>
		</xsl:call-template>
	</xsl:variable>
	<xsl:variable name="textLangDefaultPartLowerCase" select="substring($textLangDefaultLowerCase,1,2)"/>
	
	<xsl:param name="useJavascript" select="true()"/>
	<xsl:output method="html" indent="yes" version="4.01" encoding="iso-8859-1" doctype-system="http://www.w3.org/TR/html4/strict.dtd"
		doctype-public="-//W3C//DTD HTML 4.01//EN"/>
	<!-- global variable title -->
	<xsl:variable name="title">
		<xsl:choose>
			<xsl:when test="string-length(/n1:ClinicalDocument/n1:title)  &gt;= 1">
				<xsl:value-of select="/n1:ClinicalDocument/n1:title"/>
			</xsl:when>
			<xsl:when test="/n1:ClinicalDocument/n1:code/@displayName">
				<xsl:value-of select="/n1:ClinicalDocument/n1:code/@displayName"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'Clinical Document'"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:if test="n1:ClinicalDocument/n1:confidentialityCode[@code and not(@code='N')]">
			<xsl:call-template name="getLocalizedString">
				<xsl:with-param name="pre" select="' ('"/>
				<xsl:with-param name="key" select="concat(n1:ClinicalDocument/n1:confidentialityCode/@codeSystem,'-',n1:ClinicalDocument/n1:confidentialityCode/@code)"/>
				<xsl:with-param name="post" select="')'"/>
			</xsl:call-template>
		</xsl:if>
	</xsl:variable>
	<!-- Main -->
	<xsl:template match="/">
		<xsl:apply-templates select="n1:ClinicalDocument"/>
	</xsl:template>
	<!-- produce browser rendered, human readable clinical document	-->
	<xsl:template match="n1:ClinicalDocument">
		<html>
			<head>
				<xsl:comment> Do NOT edit this HTML directly: it was generated via an XSLT transformation from a CDA Release 2 XML document. </xsl:comment>
				<title>
					<xsl:value-of select="$title"/>
				</title>
				<xsl:call-template name="addCSS"/>
				<xsl:if test="$useJavascript=true()">
					<xsl:call-template name="addJS"/>
				</xsl:if>
			</head>
			<body>
				<xsl:choose>
					<xsl:when test="//n1:nonXMLBody">
						<h1 class="h1center">
							<xsl:value-of select="$title"/>
						</h1>
						<!-- START display top portion of clinical document -->
						<xsl:call-template name="recordTarget"/>
						<!-- END display top portion of clinical document -->
						<hr align="left" color="teal" size="2" width="80%"/>
						<a name="toc">&#160;</a>
						<!-- produce human readable document content -->
						<xsl:apply-templates select="n1:component/n1:structuredBody|n1:component/n1:nonXMLBody"/>
						<hr align="left" color="teal" size="2" width="80%"/>
						<xsl:call-template name="documentGeneral"/>
						<xsl:call-template name="documentationOf"/>
						<xsl:call-template name="author"/>
						<xsl:call-template name="componentof"/>
						<xsl:call-template name="participant"/>
						<xsl:call-template name="dataEnterer"/>
						<xsl:call-template name="authenticator"/>
						<xsl:call-template name="informant"/>
						<xsl:call-template name="informationRecipient"/>
						<xsl:call-template name="legalAuthenticator"/>
						<xsl:call-template name="custodian"/>
						<br/>
					</xsl:when>
					<xsl:otherwise>
						<div id="contentheader">
							<div class="innertube">
								<h1 class="h1center">
									<xsl:value-of select="$title"/>
								</h1>
								<!-- START display top portion of clinical document -->
								<xsl:call-template name="fixedHeader"/>
								<!-- END display top portion of clinical document -->
							</div>
						</div>
						<div id="contentmain">
							<div class="innertube">
								<a name="toc">&#160;</a>
								<!-- START TOC and Revision toggle -->
								<xsl:if test="//n1:content[@revised] or count(n1:component/n1:structuredBody/n1:component[n1:section]) &gt; 1">
									<table class="header_table" border="0" cellpadding="0" cellspacing="0">
										<tbody>
											<tr style="background-color:#ffffff;">
												<xsl:if test="$useJavascript">
													<xsl:call-template name="make-revisiontoggle"/>
												</xsl:if>
												<xsl:call-template name="make-tableofcontents"/>
											</tr>
										</tbody>
									</table>
								</xsl:if>
								<!-- END TOC and Revision toggle -->
								<!--hr align="left" color="teal" size="2" width="80%"/-->
								<!-- produce human readable document content -->
								<xsl:apply-templates select="n1:component/n1:structuredBody|n1:component/n1:nonXMLBody"/>
								<hr align="left" color="teal" size="2" width="80%"/>
								<xsl:call-template name="documentGeneral"/>
								<xsl:call-template name="documentationOf"/>
								<xsl:call-template name="recordTarget"/>
								<xsl:call-template name="author"/>
								<xsl:call-template name="componentof"/>
								<xsl:call-template name="participant"/>
								<xsl:call-template name="dataEnterer"/>
								<xsl:call-template name="authenticator"/>
								<xsl:call-template name="informant"/>
								<xsl:call-template name="informationRecipient"/>
								<xsl:call-template name="legalAuthenticator"/>
								<xsl:call-template name="custodian"/>
								<br/>
							</div>
						</div>
					</xsl:otherwise>
				</xsl:choose>
			</body>
		</html>
	</xsl:template>
	
	<!-- fixedHeader -->
	<xsl:template name="fixedHeader">
		<table class="header_table">
			<tbody>
				<!-- Patient row - will get only 1 patient -->
				<xsl:for-each select="/n1:ClinicalDocument/n1:recordTarget[1]/n1:patientRole">
					<tr>
						<td class="td_label" bgcolor="#3399ff">
							<i>
								<xsl:call-template name="getLocalizedString">
									<xsl:with-param name="key" select="'recordTarget'"/>
									<xsl:with-param name="post" select="': '"/>
								</xsl:call-template>
							</i>
							<b>
								<span style="font-size: 13px">
									<xsl:call-template name="show-name">
										<xsl:with-param name="name" select="n1:patient/n1:name"/>
									</xsl:call-template>
								</span>
							</b>
						</td>
						<td class="td_label" bgcolor="#3399ff" align="right">
							<i>
								<xsl:call-template name="getLocalizedString">
									<xsl:with-param name="key" select="'birthTimeLong'"/>
									<xsl:with-param name="post" select="': '"/>
								</xsl:call-template>
							</i>
							<b>
								<xsl:call-template name="formatDateTime">
									<xsl:with-param name="date" select="substring(n1:patient/n1:birthTime/@value,1,8)"/>
								</xsl:call-template>
								<xsl:text> (</xsl:text>
								<xsl:call-template name="getAge">
									<xsl:with-param name="date" select="n1:patient/n1:birthTime/@value"/>
								</xsl:call-template>
								<xsl:text>jr)</xsl:text>
							</b>
							<xsl:text>&#160; </xsl:text>
							<i>
								<xsl:call-template name="getLocalizedString">
									<xsl:with-param name="key" select="'administrativeGenderCode'"/>
									<xsl:with-param name="post" select="': '"/>
								</xsl:call-template>
							</i>
							<b>
								<xsl:for-each select="n1:patient/n1:administrativeGenderCode">
									<xsl:call-template name="show-gender"/>
								</xsl:for-each>
							</b>
							<xsl:text>&#160; </xsl:text>
							<span>
								<xsl:attribute name="title">
									<xsl:for-each select="n1:id">
										<xsl:call-template name="show-id"/>
										<xsl:text>&#10;</xsl:text>
									</xsl:for-each>
								</xsl:attribute>
								<xsl:choose>
									<xsl:when test="n1:id[@root='2.16.840.1.113883.2.4.6.3']">
										<i>
											<xsl:call-template name="getLocalizedString">
												<xsl:with-param name="key" select="n1:id[@root='2.16.840.1.113883.2.4.6.3']/@root"/>
												<xsl:with-param name="post" select="': '"/>
											</xsl:call-template>
										</i>
										<b>
											<xsl:value-of select="n1:id[@root='2.16.840.1.113883.2.4.6.3']/@extension"/>
										</b>
									</xsl:when>
									<xsl:otherwise>
										<i>
											<xsl:call-template name="getLocalizedString">
												<xsl:with-param name="key" select="'patientIdLong'"/>
												<xsl:with-param name="post" select="': '"/>
											</xsl:call-template>
										</i>
										<b>
											<xsl:value-of select="n1:id[not(@root='2.16.840.1.113883.2.4.6.3')][1]/@extension"/>
										</b>
									</xsl:otherwise>
								</xsl:choose>
							</span>
						</td>
					</tr>
				</xsl:for-each>
				<!-- Author row - will get only 1 author -->
				<xsl:for-each select="/n1:ClinicalDocument/n1:author[1]/n1:assignedAuthor">
					<tr>
						<td colspan="2">
							<i>
								<xsl:call-template name="getLocalizedString">
									<xsl:with-param name="key" select="'author'"/>
									<xsl:with-param name="post" select="': '"/>
								</xsl:call-template>
							</i>
							<b>
								<xsl:choose>
									<xsl:when test="n1:assignedPerson/n1:name">
										<xsl:call-template name="show-name">
											<xsl:with-param name="name" select="n1:assignedPerson/n1:name"/>
										</xsl:call-template>
									</xsl:when>
									<xsl:when test="n1:assignedAuthoringDevice/n1:softwareName">
										<xsl:value-of select="n1:assignedAuthoringDevice/n1:softwareName"/>
									</xsl:when>
								</xsl:choose>
							</b>
							<xsl:if test="n1:representedOrganization">
								<xsl:text>, </xsl:text>
								<i>
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="key" select="'organization'"/>
										<xsl:with-param name="post" select="': '"/>
									</xsl:call-template>
								</i>
								<b>
									<xsl:choose>
										<xsl:when test="n1:representedOrganization/n1:name">
											<xsl:call-template name="show-name">
												<xsl:with-param name="name" select="n1:representedOrganization/n1:name"/>
											</xsl:call-template>
										</xsl:when>
										<xsl:otherwise>
											<xsl:call-template name="show-id">
												<xsl:with-param name="id" select="n1:representedOrganization/n1:id"/>
											</xsl:call-template>
										</xsl:otherwise>
									</xsl:choose>
								</b>
							</xsl:if>
							<xsl:text>, </xsl:text>
							<i>
								<xsl:call-template name="getLocalizedString">
									<xsl:with-param name="key" select="'Created_on'"/>
									<xsl:with-param name="post" select="': '"/>
								</xsl:call-template>
							</i>
							<b>
								<xsl:call-template name="show-time">
									<xsl:with-param name="datetime" select="/n1:ClinicalDocument/n1:effectiveTime"/>
								</xsl:call-template>
							</b>
						</td>
					</tr>
				</xsl:for-each>
				<xsl:if test="n1:component/n1:structuredBody/n1:component/n1:section[n1:code[@code='48765-2' or @code='42348-3'][@codeSystem='2.16.840.1.113883.6.1']]">
					<xsl:variable name="firstWarnText1">
						<xsl:call-template name="caseDown">
							<xsl:with-param name="data"
								select="n1:component/n1:structuredBody/n1:component/n1:section[n1:code[@code='48765-2'][@codeSystem='2.16.840.1.113883.6.1']]/n1:text/text()[1]"
							/>
						</xsl:call-template>
					</xsl:variable>
					<xsl:variable name="firstWarnText2">
						<xsl:call-template name="caseDown">
							<xsl:with-param name="data"
								select="n1:component/n1:structuredBody/n1:component/n1:section[n1:code[@code='42348-3'][@codeSystem='2.16.840.1.113883.6.1']]/n1:text/text()[1]"
							/>
						</xsl:call-template>
					</xsl:variable>
					<!--xsl:if test="(contains($firstWarnText1,'geen') or contains($firstWarnText1,'niet')) or
								  (contains($firstWarnText2,'geen') or contains($firstWarnText2,'niet'))"-->
						<tr>
							<td>
								<!-- Waarschuwingen -->
								<xsl:choose>
									<xsl:when test="not(contains($firstWarnText1,'geen') or contains($firstWarnText1,'niet'))">
									<b>
										<a
											href="#{generate-id(n1:component/n1:structuredBody/n1:component/n1:section[n1:code[@code='48765-2'][@codeSystem='2.16.840.1.113883.6.1']]/n1:title)}">
											<xsl:text>Er zijn waarschuwingen</xsl:text>
										</a>
									</b>
									</xsl:when>
									<xsl:otherwise>
										<xsl:text>Er zijn geen waarschuwingen</xsl:text>
									</xsl:otherwise>
								</xsl:choose>
								&#160;
							</td>
							<td>
								<!-- Behandelaanwijzigingen -->
								<xsl:choose>
									<xsl:when test="not(contains($firstWarnText2,'geen') or contains($firstWarnText2,'niet'))">
									<b>
										<a
											href="#{generate-id(n1:component/n1:structuredBody/n1:component/n1:section[n1:code[@code='42348-3'][@codeSystem='2.16.840.1.113883.6.1']]/n1:title)}">
											<xsl:text>Er zijn behandelrestricties</xsl:text>
										</a>
									</b>
									</xsl:when>
									<xsl:otherwise>
										<xsl:text>Er zijn geen behandelrestricties</xsl:text>
									</xsl:otherwise>
								</xsl:choose>
								&#160;
							</td>
						</tr>
					</xsl:if>
				<!--/xsl:if-->
			</tbody>
		</table>
	</xsl:template>
	
	<!-- getAge -->
	<xsl:template name="getAge">
		<xsl:param name="date"/>
		<xsl:variable name="yearNum" select="substring ($date, 1, 4)"/>
		<xsl:variable name="monthNum" select="substring ($date, 5, 2)"/>
		<xsl:variable name="dayNum">
			<xsl:choose>
				<xsl:when test="substring ($date, 7, 1)=&quot;0&quot;">
					<xsl:value-of select="substring ($date, 8, 1)"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="substring ($date, 7, 2)"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		
		<xsl:variable name="yearNumCreate" select="substring (/n1:ClinicalDocument/n1:effectiveTime/@value, 1, 4)"/>
		<xsl:variable name="monthNumCreate" select="substring (/n1:ClinicalDocument/n1:effectiveTime/@value, 5, 2)"/>
		<xsl:variable name="dayNumCreate">
			<xsl:choose>
				<xsl:when test="substring (/n1:ClinicalDocument/n1:effectiveTime/@value, 7, 1)=&quot;0&quot;">
					<xsl:value-of select="substring (/n1:ClinicalDocument/n1:effectiveTime/@value, 8, 1)"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="substring (/n1:ClinicalDocument/n1:effectiveTime/@value, 7, 2)"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		
		<xsl:variable name="yearDiff" select="number($yearNumCreate) - number($yearNum)"/>
		<xsl:choose>
			<xsl:when test="number($monthNum) &lt; number($monthNumCreate)">
				<xsl:value-of select="$yearDiff"/>
			</xsl:when>
			<xsl:when test="number($monthNum) &gt; number($monthNumCreate)">
				<xsl:value-of select="$yearDiff - 1"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:choose>
					<xsl:when test="number($dayNum) &lt;= number($dayNumCreate)">
						<xsl:value-of select="$yearDiff"/>
					</xsl:when>
					<xsl:when test="number($dayNum) &gt; number($dayNumCreate)">
						<xsl:value-of select="$yearDiff - 1"/>
					</xsl:when>
				</xsl:choose>
			</xsl:otherwise>
		</xsl:choose>
		
	</xsl:template>
	
	<!-- generate revision toggle -->
	<xsl:template name="make-revisiontoggle">
		<td>
			<xsl:if test="//n1:content[@revised]">
				<!-- creates toggle for revisions marks -->
				<div id="revisionToggleOn" class="span_button" onclick="showReviewMarks();">
					<xsl:call-template name="getLocalizedString">
						<xsl:with-param name="key" select="'show revisions'"/>
					</xsl:call-template>
				</div>
				<div id="revisionToggleOff" style="display: none;" class="span_button" onclick="hideReviewMarks();">
					<xsl:call-template name="getLocalizedString">
						<xsl:with-param name="key" select="'hide revisions'"/>
					</xsl:call-template>
				</div>
			</xsl:if>
		</td>
	</xsl:template>
	<!-- generate table of contents -->
	<xsl:template name="make-tableofcontents">
		<xsl:variable name="tocid">
			<xsl:choose>
				<xsl:when test="$useJavascript"><xsl:text>nav</xsl:text></xsl:when>
				<xsl:otherwise><xsl:text>nonav</xsl:text></xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:if test="count(n1:component/n1:structuredBody/n1:component[n1:section]) &gt; 1">
			<td width="35%">
				<!-- produce table of contents -->
				<ul id="{$tocid}">
					<li style="list-style: none;">
						<div class="span_button">
							<xsl:call-template name="getLocalizedString">
								<xsl:with-param name="key" select="'Table of Contents'"/>
							</xsl:call-template>
							<xsl:text> &#8711;</xsl:text>
						</div>
						<ul>
							<xsl:for-each select="n1:component/n1:structuredBody/n1:component/n1:section/n1:title">
								<li>
									<a href="#{generate-id(.)}">
										<xsl:value-of select="."/>
									</a>
								</li>
							</xsl:for-each>
						</ul>
					</li>
				</ul>
			</td>
		</xsl:if>
	</xsl:template>
	<!-- header elements -->
	<xsl:template name="documentGeneral">
		<table class="header_table">
			<tbody>
				<tr>
					<td width="20%" bgcolor="#3399ff">
						<span class="td_label">
							<xsl:call-template name="getLocalizedString">
								<xsl:with-param name="key" select="'Document Id'"/>
							</xsl:call-template>
						</span>
					</td>
					<td width="30%">
						<xsl:call-template name="show-id">
							<xsl:with-param name="id" select="n1:id"/>
						</xsl:call-template>
					</td>
					<td width="15%" bgcolor="#3399ff">
						<span class="td_label">
							<xsl:call-template name="getLocalizedString">
								<xsl:with-param name="key" select="'Created_on'"/>
								<xsl:with-param name="post" select="':'"/>
							</xsl:call-template>
						</span>
					</td>
					<td>
						<xsl:call-template name="show-time">
							<xsl:with-param name="datetime" select="n1:effectiveTime"/>
						</xsl:call-template>
					</td>
				</tr>
				
			</tbody>
		</table>
	</xsl:template>
	<!-- confidentiality -->
	<xsl:template name="confidentiality">
		<table class="header_table">
			<tbody>
				<td width="20%" bgcolor="#3399ff">
					<xsl:call-template name="getLocalizedString">
						<xsl:with-param name="key" select="'Confidentiality'"/>
					</xsl:call-template>
				</td>
				<td width="80%">
					<xsl:call-template name="getLocalizedString">
						<xsl:with-param name="key" select="concat(n1:confidentialityCode/@codeSystem,'-',n1:confidentialityCode/@code)"/>
					</xsl:call-template>
					<xsl:if test="n1:confidentialityCode/n1:originalText">
						<xsl:text> </xsl:text>
						<xsl:value-of select="n1:confidentialityCode/n1:originalText"/>
					</xsl:if>
				</td>
			</tbody>
		</table>
	</xsl:template>
	<!-- author -->
	<xsl:template name="author">
		<xsl:if test="n1:author">
			<table class="header_table">
				<tbody>
					<xsl:for-each select="n1:author/n1:assignedAuthor">
						<tr>
							<td width="20%" bgcolor="#3399ff">
								<span class="td_label">
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="key" select="'author'"/>
									</xsl:call-template>
								</span>
							</td>
							<td width="80%">
								<xsl:choose>
									<xsl:when test="n1:assignedPerson/n1:name">
										<xsl:call-template name="show-name">
											<xsl:with-param name="name" select="n1:assignedPerson/n1:name"/>
										</xsl:call-template>
									</xsl:when>
									<xsl:when test="n1:assignedAuthoringDevice/n1:softwareName">
										<xsl:value-of select="n1:assignedAuthoringDevice/n1:softwareName"/>
									</xsl:when>
									<xsl:otherwise>
										<xsl:for-each select="n1:id">
											<xsl:if test="position() &gt; 1">
												<xsl:text>, </xsl:text>
											</xsl:if>
											<xsl:call-template name="show-id"/>
										</xsl:for-each>
									</xsl:otherwise>
								</xsl:choose>
								<xsl:if test="n1:representedOrganization">
									<xsl:text>, </xsl:text>
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="key" select="'organization'"/>
										<xsl:with-param name="post" select="': '"/>
									</xsl:call-template>
								</xsl:if>
								<xsl:choose>
									<xsl:when test="n1:representedOrganization/n1:name">
										<xsl:call-template name="show-name">
											<xsl:with-param name="name" select="n1:representedOrganization/n1:name"/>
										</xsl:call-template>
									</xsl:when>
									<xsl:otherwise>
										<xsl:for-each select="n1:representedOrganization/n1:id">
											<xsl:call-template name="show-id"/>
											<br/>
										</xsl:for-each>
									</xsl:otherwise>
								</xsl:choose>
							</td>
						</tr>
						<xsl:if test="n1:addr | n1:telecom">
							<tr>
								<td bgcolor="#3399ff">
									<span class="td_label">
										<xsl:call-template name="getLocalizedString">
											<xsl:with-param name="key" select="'Contact_details'"/>
										</xsl:call-template>
									</span>
								</td>
								<td>
									<xsl:call-template name="show-contactInfo">
										<xsl:with-param name="contact" select="."/>
									</xsl:call-template>
								</td>
							</tr>
						</xsl:if>
						<xsl:if test="n1:representedOrganization/n1:addr | n1:representedOrganization/n1:telecom">
							<tr>
								<td bgcolor="#3399ff">
									<span class="td_label">
										<xsl:call-template name="getLocalizedString">
											<xsl:with-param name="key" select="'Contact_details'"/>
										</xsl:call-template>
										<xsl:text> (</xsl:text>
										<xsl:call-template name="getLocalizedString">
											<xsl:with-param name="key" select="'organization'"/>
										</xsl:call-template>
										<xsl:text>)</xsl:text>
									</span>
								</td>
								<td>
									<xsl:call-template name="show-contactInfo">
										<xsl:with-param name="contact" select="n1:representedOrganization"/>
									</xsl:call-template>
								</td>
							</tr>
						</xsl:if>
					</xsl:for-each>
				</tbody>
			</table>
		</xsl:if>
	</xsl:template>
	<!-- 	authenticator -->
	<xsl:template name="authenticator">
		<xsl:if test="n1:authenticator">
			<table class="header_table">
				<tbody>
					<tr>
						<xsl:for-each select="n1:authenticator">
							<tr>
								<td width="20%" bgcolor="#3399ff">
									<span class="td_label">
										<xsl:call-template name="getLocalizedString">
											<xsl:with-param name="key" select="'Signed'"/>
											<xsl:with-param name="post" select="' '"/>
										</xsl:call-template>
									</span>
								</td>
								<td width="80%">
									<xsl:call-template name="show-name">
										<xsl:with-param name="name" select="n1:assignedEntity/n1:assignedPerson/n1:name"/>
									</xsl:call-template>
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="pre" select="'&#160;'"/>
										<xsl:with-param name="key" select="'at'"/>
										<xsl:with-param name="post" select="'&#160;'"/>
									</xsl:call-template>
									<xsl:call-template name="show-time">
										<xsl:with-param name="date" select="n1:time"/>
									</xsl:call-template>
								</td>
							</tr>
							<xsl:if test="n1:assignedEntity/n1:addr | n1:assignedEntity/n1:telecom">
								<tr>
									<td bgcolor="#3399ff">
										<span class="td_label">
											<xsl:call-template name="getLocalizedString">
												<xsl:with-param name="key" select="'Contact_details'"/>
											</xsl:call-template>
										</span>
									</td>
									<td width="80%">
										<xsl:call-template name="show-contactInfo">
											<xsl:with-param name="contact" select="n1:assignedEntity"/>
										</xsl:call-template>
									</td>
								</tr>
							</xsl:if>
						</xsl:for-each>
					</tr>
				</tbody>
			</table>
		</xsl:if>
	</xsl:template>
	<!-- legalAuthenticator -->
	<xsl:template name="legalAuthenticator">
		<xsl:if test="n1:legalAuthenticator">
			<table class="header_table">
				<tbody>
					<tr>
						<td width="20%" bgcolor="#3399ff">
							<span class="td_label">
								<xsl:call-template name="getLocalizedString">
									<xsl:with-param name="key" select="'typeCode-LA'"/>
								</xsl:call-template>
							</span>
						</td>
						<td width="80%">
							<xsl:call-template name="show-assignedEntity">
								<xsl:with-param name="asgnEntity" select="n1:legalAuthenticator/n1:assignedEntity"/>
							</xsl:call-template>
							<xsl:text> </xsl:text>
							<xsl:call-template name="show-sig">
								<xsl:with-param name="sig" select="n1:legalAuthenticator/n1:signatureCode"/>
							</xsl:call-template>
							<xsl:if test="n1:legalAuthenticator/n1:time/@value">
								<xsl:call-template name="getLocalizedString">
									<xsl:with-param name="pre" select="'&#160;'"/>
									<xsl:with-param name="key" select="'at'"/>
									<xsl:with-param name="post" select="'&#160;'"/>
								</xsl:call-template>
								<xsl:call-template name="show-time">
									<xsl:with-param name="datetime" select="n1:legalAuthenticator/n1:time"/>
								</xsl:call-template>
							</xsl:if>
						</td>
					</tr>
					<xsl:if test="n1:legalAuthenticator/n1:assignedEntity/n1:addr | n1:legalAuthenticator/n1:assignedEntity/n1:telecom">
						<tr>
							<td bgcolor="#3399ff">
								<span class="td_label">
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="key" select="'Contact_details'"/>
									</xsl:call-template>
								</span>
							</td>
							<td>
								<xsl:call-template name="show-contactInfo">
									<xsl:with-param name="contact" select="n1:legalAuthenticator/n1:assignedEntity"/>
								</xsl:call-template>
							</td>
						</tr>
					</xsl:if>
				</tbody>
			</table>
		</xsl:if>
	</xsl:template>
	<!-- dataEnterer -->
	<xsl:template name="dataEnterer">
		<xsl:if test="n1:dataEnterer">
			<table class="header_table">
				<tbody>
					<tr>
						<td width="20%" bgcolor="#3399ff">
							<span class="td_label">
								<xsl:call-template name="getLocalizedString">
									<xsl:with-param name="key" select="'typeCode-ENT'"/>
								</xsl:call-template>
							</span>
						</td>
						<td width="80%">
							<xsl:call-template name="show-assignedEntity">
								<xsl:with-param name="asgnEntity" select="n1:dataEnterer/n1:assignedEntity"/>
							</xsl:call-template>
						</td>
					</tr>
					<xsl:if test="n1:dataEnterer/n1:assignedEntity/n1:addr | n1:dataEnterer/n1:assignedEntity/n1:telecom">
						<tr>
							<td bgcolor="#3399ff">
								<span class="td_label">
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="key" select="'Contact_details'"/>
									</xsl:call-template>
								</span>
							</td>
							<td>
								<xsl:call-template name="show-contactInfo">
									<xsl:with-param name="contact" select="n1:dataEnterer/n1:assignedEntity"/>
								</xsl:call-template>
							</td>
						</tr>
					</xsl:if>
				</tbody>
			</table>
		</xsl:if>
	</xsl:template>
	<!-- componentOf -->
	<xsl:template name="componentof">
		<xsl:if test="n1:componentOf">
			<table class="header_table">
				<tbody>
					<xsl:for-each select="n1:componentOf/n1:encompassingEncounter">
						<xsl:if test="n1:id">
							<tr>
								<xsl:choose>
									<xsl:when test="n1:code">
										<td width="20%" bgcolor="#3399ff">
											<span class="td_label">
												<xsl:call-template name="getLocalizedString">
													<xsl:with-param name="key" select="'Encounter Id'"/>
												</xsl:call-template>
											</span>
										</td>
										<td width="30%">
											<xsl:call-template name="show-id">
												<xsl:with-param name="id" select="n1:id"/>
											</xsl:call-template>
										</td>
										<td width="15%" bgcolor="#3399ff">
											<span class="td_label">
												<xsl:call-template name="getLocalizedString">
													<xsl:with-param name="key" select="'Encounter Type'"/>
												</xsl:call-template>
											</span>
										</td>
										<td>
											<xsl:call-template name="show-code">
												<xsl:with-param name="code" select="n1:code"/>
											</xsl:call-template>
										</td>
									</xsl:when>
									<xsl:otherwise>
										<td width="20%" bgcolor="#3399ff">
											<span class="td_label">
												<xsl:call-template name="getLocalizedString">
													<xsl:with-param name="key" select="'Encounter Id'"/>
												</xsl:call-template>
											</span>
										</td>
										<td colspan="3">
											<xsl:call-template name="show-id">
												<xsl:with-param name="id" select="n1:id"/>
											</xsl:call-template>
										</td>
									</xsl:otherwise>
								</xsl:choose>
							</tr>
						</xsl:if>
						<tr>
							<td width="20%" bgcolor="#3399ff">
								<span class="td_label">
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="key" select="'Encounter Date'"/>
									</xsl:call-template>
								</span>
							</td>
							<td colspan="3">
								<xsl:if test="n1:effectiveTime">
									<xsl:choose>
										<xsl:when test="n1:effectiveTime/@value">
											<xsl:call-template name="getLocalizedString">
												<xsl:with-param name="pre" select="'&#160;'"/>
												<xsl:with-param name="key" select="'at'"/>
												<xsl:with-param name="post" select="'&#160;'"/>
											</xsl:call-template>
											<xsl:call-template name="show-time">
												<xsl:with-param name="datetime" select="n1:effectiveTime"/>
											</xsl:call-template>
										</xsl:when>
										<xsl:when test="n1:effectiveTime/n1:low">
											<xsl:call-template name="getLocalizedString">
												<xsl:with-param name="pre" select="'&#160;'"/>
												<xsl:with-param name="key" select="'from'"/>
												<xsl:with-param name="post" select="'&#160;'"/>
											</xsl:call-template>
											<xsl:call-template name="show-time">
												<xsl:with-param name="datetime" select="n1:effectiveTime/n1:low"/>
											</xsl:call-template>
											<xsl:if test="n1:effectiveTime/n1:high">
												<xsl:call-template name="getLocalizedString">
													<xsl:with-param name="pre" select="'&#160;'"/>
													<xsl:with-param name="key" select="'to'"/>
													<xsl:with-param name="post" select="'&#160;'"/>
												</xsl:call-template>
												<xsl:call-template name="show-time">
													<xsl:with-param name="datetime" select="n1:effectiveTime/n1:high"/>
												</xsl:call-template>
											</xsl:if>
										</xsl:when>
									</xsl:choose>
								</xsl:if>
							</td>
						</tr>
						<xsl:if test="n1:location/n1:healthCareFacility">
							<tr>
								<td width="20%" bgcolor="#3399ff">
									<span class="td_label">
										<xsl:call-template name="getLocalizedString">
											<xsl:with-param name="key" select="'Encounter Location'"/>
										</xsl:call-template>
									</span>
								</td>
								<td colspan="3">
									<xsl:choose>
										<xsl:when test="n1:location/n1:healthCareFacility/n1:location/n1:name">
											<xsl:call-template name="show-name">
												<xsl:with-param name="name" select="n1:location/n1:healthCareFacility/n1:location/n1:name"/>
											</xsl:call-template>
											<xsl:for-each select="n1:location/n1:healthCareFacility/n1:serviceProviderOrganization/n1:name">
												<xsl:call-template name="getLocalizedString">
													<xsl:with-param name="pre" select="'&#160;'"/>
													<xsl:with-param name="key" select="'of'"/>
													<xsl:with-param name="post" select="'&#160;'"/>
												</xsl:call-template>
												<xsl:call-template name="show-name">
													<xsl:with-param name="name" select="n1:location/n1:healthCareFacility/n1:serviceProviderOrganization/n1:name"/>
												</xsl:call-template>
											</xsl:for-each>
										</xsl:when>
										<xsl:when test="n1:location/n1:healthCareFacility/n1:code">
											<xsl:call-template name="show-code">
												<xsl:with-param name="code" select="n1:location/n1:healthCareFacility/n1:code"/>
											</xsl:call-template>
										</xsl:when>
										<xsl:otherwise>
											<xsl:if test="n1:location/n1:healthCareFacility/n1:id">
												<xsl:call-template name="getLocalizedString">
													<xsl:with-param name="key" select="'id'"/>
													<xsl:with-param name="post" select="':'"/>
												</xsl:call-template>
												<xsl:for-each select="n1:location/n1:healthCareFacility/n1:id">
													<xsl:call-template name="show-id">
														<xsl:with-param name="id" select="."/>
													</xsl:call-template>
												</xsl:for-each>
											</xsl:if>
										</xsl:otherwise>
									</xsl:choose>
								</td>
							</tr>
						</xsl:if>
						<xsl:if test="n1:responsibleParty">
							<tr>
								<td width="20%" bgcolor="#3399ff">
									<span class="td_label">
										<xsl:call-template name="getLocalizedString">
											<xsl:with-param name="key" select="'responsibleParty'"/>
										</xsl:call-template>
									</span>
								</td>
								<td colspan="3">
									<xsl:call-template name="show-assignedEntity">
										<xsl:with-param name="asgnEntity" select="n1:responsibleParty/n1:assignedEntity"/>
									</xsl:call-template>
								</td>
							</tr>
						</xsl:if>
						<xsl:if test="n1:responsibleParty/n1:assignedEntity/n1:addr | n1:responsibleParty/n1:assignedEntity/n1:telecom">
							<tr>
								<td width="20%" bgcolor="#3399ff">
									<span class="td_label">
										<xsl:call-template name="getLocalizedString">
											<xsl:with-param name="key" select="'Contact_details'"/>
										</xsl:call-template>
									</span>
								</td>
								<td colspan="3">
									<xsl:call-template name="show-contactInfo">
										<xsl:with-param name="contact" select="n1:responsibleParty/n1:assignedEntity"/>
									</xsl:call-template>
								</td>
							</tr>
						</xsl:if>
					</xsl:for-each>
				</tbody>
			</table>
		</xsl:if>
	</xsl:template>
	<!-- custodian -->
	<xsl:template name="custodian">
		<xsl:if test="n1:custodian">
			<table class="header_table">
				<tbody>
					<tr>
						<td width="20%" bgcolor="#3399ff">
							<span class="td_label">
								<xsl:call-template name="getLocalizedString">
									<xsl:with-param name="key" select="'custodian'"/>
								</xsl:call-template>
							</span>
						</td>
						<td width="80%">
							<xsl:choose>
								<xsl:when test="n1:custodian/n1:assignedCustodian/n1:representedCustodianOrganization/n1:name">
									<xsl:call-template name="show-name">
										<xsl:with-param name="name"
											select="n1:custodian/n1:assignedCustodian/n1:representedCustodianOrganization/n1:name"/>
									</xsl:call-template>
								</xsl:when>
								<xsl:otherwise>
									<xsl:for-each select="n1:custodian/n1:assignedCustodian/n1:representedCustodianOrganization/n1:id">
										<xsl:call-template name="show-id"/>
										<xsl:if test="position()!=last()">
											<br/>
										</xsl:if>
									</xsl:for-each>
								</xsl:otherwise>
							</xsl:choose>
						</td>
					</tr>
					<xsl:if
						test="n1:custodian/n1:assignedCustodian/n1:representedCustodianOrganization/n1:addr | n1:custodian/n1:assignedCustodian/n1:representedCustodianOrganization/n1:telecom">
						<tr>
							<td bgcolor="#3399ff">
								<span class="td_label">
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="key" select="'Contact_details'"/>
									</xsl:call-template>
								</span>
							</td>
							<td width="80%">
								<xsl:call-template name="show-contactInfo">
									<xsl:with-param name="contact" select="n1:custodian/n1:assignedCustodian/n1:representedCustodianOrganization"/>
								</xsl:call-template>
							</td>
						</tr>
					</xsl:if>
				</tbody>
			</table>
		</xsl:if>
	</xsl:template>
	<!-- documentationOf -->
	<xsl:template name="documentationOf">
		<xsl:if test="n1:documentationOf">
			<table class="header_table">
				<tbody>
					<xsl:for-each select="n1:documentationOf">
						<xsl:if test="n1:serviceEvent/@classCode and n1:serviceEvent/n1:code">
							<xsl:variable name="displayName">
								<xsl:call-template name="show-actClassCode">
									<xsl:with-param name="clsCode" select="n1:serviceEvent/@classCode"/>
								</xsl:call-template>
							</xsl:variable>
							<xsl:if test="$displayName">
								<tr>
									<td width="20%" bgcolor="#3399ff">
										<span class="td_label">
											<xsl:call-template name="firstCharCaseUp">
												<xsl:with-param name="data" select="$displayName"/>
											</xsl:call-template>
										</span>
									</td>
									<td width="80%" colspan="3">
										<xsl:call-template name="show-code">
											<xsl:with-param name="code" select="n1:serviceEvent/n1:code"/>
										</xsl:call-template>
										<xsl:if test="n1:serviceEvent/n1:effectiveTime">
											<xsl:choose>
												<xsl:when test="n1:serviceEvent/n1:effectiveTime/@value">
													<xsl:call-template name="getLocalizedString">
														<xsl:with-param name="pre" select="'&#160;'"/>
														<xsl:with-param name="key" select="'at'"/>
														<xsl:with-param name="post" select="'&#160;'"/>
													</xsl:call-template>
													<xsl:call-template name="show-time">
														<xsl:with-param name="datetime" select="n1:serviceEvent/n1:effectiveTime"/>
													</xsl:call-template>
												</xsl:when>
												<xsl:when test="n1:serviceEvent/n1:effectiveTime/n1:low">
													<xsl:call-template name="getLocalizedString">
														<xsl:with-param name="pre" select="'&#160;'"/>
														<xsl:with-param name="key" select="'from'"/>
														<xsl:with-param name="post" select="'&#160;'"/>
													</xsl:call-template>
													<xsl:call-template name="show-time">
														<xsl:with-param name="datetime" select="n1:serviceEvent/n1:effectiveTime/n1:low"/>
													</xsl:call-template>
													<xsl:if test="n1:serviceEvent/n1:effectiveTime/n1:high">
														<xsl:call-template name="getLocalizedString">
															<xsl:with-param name="pre" select="'&#160;'"/>
															<xsl:with-param name="key" select="'to'"/>
															<xsl:with-param name="post" select="'&#160;'"/>
														</xsl:call-template>
														<xsl:call-template name="show-time">
															<xsl:with-param name="datetime" select="n1:serviceEvent/n1:effectiveTime/n1:high"/>
														</xsl:call-template>
													</xsl:if>
												</xsl:when>
											</xsl:choose>
										</xsl:if>
									</td>
								</tr>
							</xsl:if>
						</xsl:if>
						<xsl:for-each select="n1:serviceEvent/n1:performer">
							<xsl:variable name="displayName">
								<xsl:call-template name="show-participationType">
									<xsl:with-param name="ptype" select="@typeCode"/>
								</xsl:call-template>
								<xsl:text> </xsl:text>
								<xsl:if test="n1:functionCode/@code">
									<xsl:call-template name="show-participationFunction">
										<xsl:with-param name="pFunction" select="n1:functionCode/@code"/>
									</xsl:call-template>
								</xsl:if>
							</xsl:variable>
							<tr>
								<td width="20%" bgcolor="#3399ff">
									<span class="td_label">
										<xsl:call-template name="firstCharCaseUp">
											<xsl:with-param name="data" select="$displayName"/>
										</xsl:call-template>
									</span>
								</td>
								<td width="80%" colspan="3">
									<xsl:call-template name="show-assignedEntity">
										<xsl:with-param name="asgnEntity" select="n1:assignedEntity"/>
									</xsl:call-template>
								</td>
							</tr>
						</xsl:for-each>
					</xsl:for-each>
				</tbody>
			</table>
		</xsl:if>
	</xsl:template>
	<!-- inFulfillmentOf -->
	<xsl:template name="inFulfillmentOf">
		<xsl:if test="n1:infulfillmentOf">
			<table class="header_table">
				<tbody>
					<xsl:for-each select="n1:inFulfillmentOf">
						<tr>
							<td width="20%" bgcolor="#3399ff">
								<span class="td_label">
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="key" select="'typeCode-FLFS'"/>
									</xsl:call-template>
								</span>
							</td>
							<td width="80%">
								<xsl:for-each select="n1:order">
									<xsl:for-each select="n1:id">
										<xsl:call-template name="show-id"/>
									</xsl:for-each>
									<xsl:for-each select="n1:code">
										<xsl:text>&#160;</xsl:text>
										<xsl:call-template name="show-code">
											<xsl:with-param name="code" select="."/>
										</xsl:call-template>
									</xsl:for-each>
									<xsl:for-each select="n1:priorityCode">
										<xsl:text>&#160;</xsl:text>
										<xsl:call-template name="show-code">
											<xsl:with-param name="code" select="."/>
										</xsl:call-template>
									</xsl:for-each>
								</xsl:for-each>
							</td>
						</tr>
					</xsl:for-each>
				</tbody>
			</table>
		</xsl:if>
	</xsl:template>
	<!-- informant -->
	<xsl:template name="informant">
		<xsl:if test="n1:informant">
			<table class="header_table">
				<tbody>
					<xsl:for-each select="n1:informant">
						<tr>
							<td width="20%" bgcolor="#3399ff">
								<span class="td_label">
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="key" select="'typeCode-INF'"/>
									</xsl:call-template>
								</span>
							</td>
							<td width="80%">
								<xsl:if test="n1:assignedEntity">
									<xsl:call-template name="show-assignedEntity">
										<xsl:with-param name="asgnEntity" select="n1:assignedEntity"/>
									</xsl:call-template>
								</xsl:if>
								<xsl:if test="n1:relatedEntity">
									<xsl:call-template name="show-relatedEntity">
										<xsl:with-param name="relatedEntity" select="n1:relatedEntity"/>
									</xsl:call-template>
								</xsl:if>
							</td>
						</tr>
						<xsl:choose>
							<xsl:when test="n1:assignedEntity/n1:addr | n1:assignedEntity/n1:telecom">
								<tr>
									<td bgcolor="#3399ff">
										<span class="td_label">
											<xsl:call-template name="getLocalizedString">
												<xsl:with-param name="key" select="'Contact_details'"/>
											</xsl:call-template>
										</span>
									</td>
									<td>
										<xsl:if test="n1:assignedEntity">
											<xsl:call-template name="show-contactInfo">
												<xsl:with-param name="contact" select="n1:assignedEntity"/>
											</xsl:call-template>
										</xsl:if>
									</td>
								</tr>
							</xsl:when>
							<xsl:when test="n1:relatedEntity/n1:addr | n1:relatedEntity/n1:telecom">
								<tr>
									<td bgcolor="#3399ff">
										<span class="td_label">
											<xsl:call-template name="getLocalizedString">
												<xsl:with-param name="key" select="'Contact_details'"/>
											</xsl:call-template>
										</span>
									</td>
									<td>
										<xsl:if test="n1:relatedEntity">
											<xsl:call-template name="show-contactInfo">
												<xsl:with-param name="contact" select="n1:relatedEntity"/>
											</xsl:call-template>
										</xsl:if>
									</td>
								</tr>
							</xsl:when>
						</xsl:choose>
					</xsl:for-each>
				</tbody>
			</table>
		</xsl:if>
	</xsl:template>
	<!-- informantionRecipient -->
	<xsl:template name="informationRecipient">
		<xsl:if test="n1:informationRecipient">
			<table class="header_table">
				<tbody>
					<xsl:for-each select="n1:informationRecipient">
						<tr>
							<td width="20%" bgcolor="#3399ff">
								<span class="td_label">
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="key" select="'typeCode-PRCP'"/>
									</xsl:call-template>
								</span>
							</td>
							<td width="80%">
								<xsl:choose>
									<xsl:when test="n1:intendedRecipient/n1:informationRecipient/n1:name">
										<xsl:for-each select="n1:intendedRecipient/n1:informationRecipient">
											<xsl:call-template name="show-name">
												<xsl:with-param name="name" select="n1:name"/>
											</xsl:call-template>
											<xsl:if test="position() != last()">
												<br/>
											</xsl:if>
										</xsl:for-each>
									</xsl:when>
									<xsl:otherwise>
										<xsl:for-each select="n1:intendedRecipient">
											<xsl:for-each select="n1:id">
												<xsl:call-template name="show-id"/>
											</xsl:for-each>
											<xsl:if test="position() != last()">
												<br/>
											</xsl:if>
											<br/>
										</xsl:for-each>
									</xsl:otherwise>
								</xsl:choose>
							</td>
						</tr>
						<xsl:if test="n1:intendedRecipient/n1:addr | n1:intendedRecipient/n1:telecom">
							<tr>
								<td bgcolor="#3399ff">
									<span class="td_label">
										<xsl:call-template name="getLocalizedString">
											<xsl:with-param name="key" select="'Contact_details'"/>
										</xsl:call-template>
									</span>
								</td>
								<td>
									<xsl:call-template name="show-contactInfo">
										<xsl:with-param name="contact" select="n1:intendedRecipient"/>
									</xsl:call-template>
								</td>
							</tr>
						</xsl:if>
					</xsl:for-each>
				</tbody>
			</table>
		</xsl:if>
	</xsl:template>
	<!-- participant -->
	<xsl:template name="participant">
		<xsl:if test="n1:participant">
			<xsl:for-each select="n1:participant">
				<table class="header_table">
					<tbody>
						<tr>
							<td width="20%" bgcolor="#3399ff">
								<xsl:variable name="participtRole">
									<xsl:call-template name="translateRoleAssoCode">
										<xsl:with-param name="classCode" select="n1:associatedEntity/@classCode"/>
										<xsl:with-param name="code" select="n1:associatedEntity/n1:code"/>
									</xsl:call-template>
								</xsl:variable>
								<xsl:choose>
									<xsl:when test="$participtRole">
										<span class="td_label">
											<xsl:call-template name="firstCharCaseUp">
												<xsl:with-param name="data" select="$participtRole"/>
											</xsl:call-template>
										</span>
									</xsl:when>
									<xsl:otherwise>
										<span class="td_label">
											<xsl:call-template name="getLocalizedString">
												<xsl:with-param name="key" select="'participant'"/>
											</xsl:call-template>
										</span>
									</xsl:otherwise>
								</xsl:choose>
							</td>
							<td width="80%">
								<xsl:if test="n1:functionCode">
									<xsl:call-template name="show-code">
										<xsl:with-param name="code" select="n1:functionCode"/>
									</xsl:call-template>
									<xsl:text>, </xsl:text>
								</xsl:if>
								<xsl:call-template name="show-associatedEntity">
									<xsl:with-param name="assoEntity" select="n1:associatedEntity"/>
								</xsl:call-template>
								<xsl:if test="n1:time">
									<xsl:if test="n1:time/n1:low">
										<xsl:call-template name="getLocalizedString">
											<xsl:with-param name="pre" select="'&#160;'"/>
											<xsl:with-param name="key" select="'from'"/>
											<xsl:with-param name="post" select="'&#160;'"/>
										</xsl:call-template>
										<xsl:call-template name="show-time">
											<xsl:with-param name="datetime" select="n1:time/n1:low"/>
										</xsl:call-template>
									</xsl:if>
									<xsl:if test="n1:time/n1:high">
										<xsl:call-template name="getLocalizedString">
											<xsl:with-param name="pre" select="'&#160;'"/>
											<xsl:with-param name="key" select="'to'"/>
											<xsl:with-param name="post" select="'&#160;'"/>
										</xsl:call-template>
										<xsl:call-template name="show-time">
											<xsl:with-param name="datetime" select="n1:time/n1:high"/>
										</xsl:call-template>
									</xsl:if>
								</xsl:if>
								<xsl:if test="position() != last()">
									<br/>
								</xsl:if>
							</td>
						</tr>
						<xsl:if test="n1:associatedEntity/n1:addr | n1:associatedEntity/n1:telecom">
							<tr>
								<td bgcolor="#3399ff">
									<span class="td_label">
										<xsl:call-template name="getLocalizedString">
											<xsl:with-param name="key" select="'Contact_details'"/>
										</xsl:call-template>
									</span>
								</td>
								<td>
									<xsl:call-template name="show-contactInfo">
										<xsl:with-param name="contact" select="n1:associatedEntity"/>
									</xsl:call-template>
								</td>
							</tr>
						</xsl:if>
					</tbody>
				</table>
			</xsl:for-each>
		</xsl:if>
	</xsl:template>
	<!-- recordTarget -->
	<xsl:template name="recordTarget">
		<table class="header_table">
			<tbody>
				<xsl:for-each select="/n1:ClinicalDocument/n1:recordTarget/n1:patientRole">
					<xsl:if test="not(n1:id/@nullFlavor)">
						<tr>
							<td width="20%" bgcolor="#3399ff">
								<span class="td_label">
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="pre" select="''"/>
										<xsl:with-param name="key" select="'recordTarget'"/>
										<xsl:with-param name="post" select="''"/>
									</xsl:call-template>
								</span>
							</td>
							<td colspan="3">
								<xsl:call-template name="show-name">
									<xsl:with-param name="name" select="n1:patient/n1:name"/>
								</xsl:call-template>
							</td>
						</tr>
						<tr>
							<td width="20%" bgcolor="#3399ff">
								<span class="td_label">
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="key" select="'birthTimeLong'"/>
									</xsl:call-template>
								</span>
							</td>
							<td width="30%">
								<xsl:call-template name="show-time">
									<xsl:with-param name="datetime" select="n1:patient/n1:birthTime"/>
								</xsl:call-template>
							</td>
							<td width="15%" bgcolor="#3399ff">
								<span class="td_label">
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="key" select="'administrativeGenderCode'"/>
									</xsl:call-template>
								</span>
							</td>
							<td>
								<xsl:for-each select="n1:patient/n1:administrativeGenderCode">
									<xsl:call-template name="show-gender"/>
								</xsl:for-each>
							</td>
						</tr>
						<xsl:if test="n1:patient/n1:raceCode | (n1:patient/n1:ethnicGroupCode)">
							<tr>
								<td width="20%" bgcolor="#3399ff">
									<span class="td_label">
										<xsl:call-template name="getLocalizedString">
											<xsl:with-param name="key" select="'Ras'"/>
										</xsl:call-template>
									</span>
								</td>
								<td width="30%">
									<xsl:choose>
										<xsl:when test="n1:patient/n1:raceCode">
											<xsl:for-each select="n1:patient/n1:raceCode">
												<xsl:call-template name="show-race-ethnicity"/>
											</xsl:for-each>
										</xsl:when>
										<xsl:otherwise>
											<xsl:call-template name="getLocalizedString">
												<xsl:with-param name="key" select="'Information not available'"/>
											</xsl:call-template>
										</xsl:otherwise>
									</xsl:choose>
								</td>
								<td width="15%" bgcolor="#3399ff">
									<span class="td_label">
										<xsl:call-template name="getLocalizedString">
											<xsl:with-param name="key" select="'Ethnicity'"/>
										</xsl:call-template>
									</span>
								</td>
								<td>
									<xsl:choose>
										<xsl:when test="n1:patient/n1:ethnicGroupCode">
											<xsl:for-each select="n1:patient/n1:ethnicGroupCode">
												<xsl:call-template name="show-race-ethnicity"/>
											</xsl:for-each>
										</xsl:when>
										<xsl:otherwise>
											<xsl:call-template name="getLocalizedString">
												<xsl:with-param name="key" select="'Information not available'"/>
											</xsl:call-template>
										</xsl:otherwise>
									</xsl:choose>
								</td>
							</tr>
						</xsl:if>
						<tr>
							<td bgcolor="#3399ff">
								<span class="td_label">
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="key" select="'Contact_details'"/>
									</xsl:call-template>
								</span>
							</td>
							<td>
								<xsl:call-template name="show-contactInfo">
									<xsl:with-param name="contact" select="."/>
								</xsl:call-template>
							</td>
							<td bgcolor="#3399ff">
								<span class="td_label">
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="key" select="'patientIdsLong'"/>
									</xsl:call-template>
								</span>
							</td>
							<td>
								<xsl:for-each select="n1:id">
									<xsl:call-template name="show-id"/>
									<br/>
								</xsl:for-each>
							</td>
						</tr>
					</xsl:if>
				</xsl:for-each>
			</tbody>
		</table>
	</xsl:template>
	<!-- relatedDocument -->
	<xsl:template name="relatedDocument">
		<xsl:if test="n1:relatedDocument">
			<table class="header_table">
				<tbody>
					<xsl:for-each select="n1:relatedDocument">
						<tr>
							<td width="20%" bgcolor="#3399ff">
								<span class="td_label">
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="key" select="'relatedDocument'"/>
									</xsl:call-template>
								</span>
							</td>
							<td width="80%">
								<xsl:for-each select="n1:parentDocument">
									<xsl:for-each select="n1:id">
										<xsl:call-template name="show-id"/>
										<br/>
									</xsl:for-each>
								</xsl:for-each>
							</td>
						</tr>
					</xsl:for-each>
				</tbody>
			</table>
		</xsl:if>
	</xsl:template>
	<!-- authorization (consent) -->
	<xsl:template name="authorization">
		<xsl:if test="n1:authorization">
			<table class="header_table">
				<tbody>
					<xsl:for-each select="n1:authorization">
						<tr>
							<td width="20%" bgcolor="#3399ff">
								<span class="td_label">
									<xsl:call-template name="getLocalizedString">
										<xsl:with-param name="key" select="'consent'"/>
									</xsl:call-template>
								</span>
							</td>
							<td width="80%">
								<xsl:choose>
									<xsl:when test="n1:consent/n1:code">
										<xsl:call-template name="show-code">
											<xsl:with-param name="code" select="n1:consent/n1:code"/>
										</xsl:call-template>
									</xsl:when>
									<xsl:otherwise>
										<xsl:call-template name="show-code">
											<xsl:with-param name="code" select="n1:consent/n1:statusCode"/>
										</xsl:call-template>
									</xsl:otherwise>
								</xsl:choose>
								<br/>
							</td>
						</tr>
					</xsl:for-each>
				</tbody>
			</table>
		</xsl:if>
	</xsl:template>
	<!-- setAndVersion -->
	<xsl:template name="setAndVersion">
		<xsl:if test="n1:setId and n1:versionNumber">
			<table class="header_table">
				<tbody>
					<tr>
						<td width="20%">
							<xsl:call-template name="getLocalizedString">
								<xsl:with-param name="key" select="'SetId and Version'"/>
							</xsl:call-template>
						</td>
						<td colspan="3">
							<xsl:call-template name="getLocalizedString">
								<xsl:with-param name="key" select="'setId'"/>
								<xsl:with-param name="post" select="': '"/>
							</xsl:call-template>
							<xsl:call-template name="show-id">
								<xsl:with-param name="id" select="n1:setId"/>
							</xsl:call-template>
							<xsl:call-template name="getLocalizedString">
								<xsl:with-param name="key" select="'versionNumber'"/>
								<xsl:with-param name="post" select="': '"/>
							</xsl:call-template>
							<xsl:value-of select="n1:versionNumber/@value"/>
						</td>
					</tr>
				</tbody>
			</table>
		</xsl:if>
	</xsl:template>
	<!-- show StructuredBody 	-->
	<xsl:template match="n1:component/n1:structuredBody">
		<xsl:for-each select="n1:component/n1:section">
			<xsl:call-template name="section"/>
		</xsl:for-each>
	</xsl:template>
	<!-- show nonXMLBody -->
	<xsl:template match="n1:component/n1:nonXMLBody">
		<xsl:choose>
			<!-- if there is a reference, use that in an IFRAME -->
			<xsl:when test="n1:text/n1:reference">
				<IFRAME name='nonXMLBody' id='nonXMLBody' WIDTH='80%' HEIGHT='600' src='{n1:text/n1:reference/@value}'/>
			</xsl:when>
			<xsl:when test="not(n1:text/@mediaType) or n1:text/@mediaType='text/plain'">
				<pre>
                    <xsl:value-of select="n1:text/text()"/>
                </pre>
			</xsl:when>
			<xsl:otherwise>
				<center>
					<xsl:call-template name="getLocalizedString">
						<xsl:with-param name="key" select="'Cannot display the text'"/>
					</xsl:call-template>
				</center>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- top level component/section: display title and text,
     and process any nested component/sections
	 -->
	<xsl:template name="section">
		<xsl:call-template name="section-title">
			<xsl:with-param name="title" select="n1:title"/>
		</xsl:call-template>
		<xsl:call-template name="section-author"/>
		<xsl:call-template name="section-informant"/>
		<xsl:call-template name="section-subject"/>
		<xsl:call-template name="section-text"/>
		<xsl:for-each select="n1:component/n1:section">
			<xsl:call-template name="nestedSection">
				<xsl:with-param name="margin" select="2"/>
			</xsl:call-template>
		</xsl:for-each>
	</xsl:template>
	<!-- top level section title -->
	<xsl:template name="section-title">
		<xsl:param name="title"/>
		<xsl:variable name="locString">
			<xsl:variable name="subLocString">
			<xsl:call-template name="getLocalizedString">
				<xsl:with-param name="key" select="concat(n1:code/@codeSystem,'-',n1:code/@code)"/>
			</xsl:call-template>
			</xsl:variable>
			<xsl:choose>
				<xsl:when test="$subLocString!=concat(n1:code/@codeSystem,'-',n1:code/@code)">
					<xsl:value-of select="$subLocString"/>
				</xsl:when>
				<xsl:when test="n1:code/@displayName">
					<xsl:value-of select="$subLocString"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="''"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:choose>
			<xsl:when test="count(/n1:ClinicalDocument/n1:component/n1:structuredBody/n1:component[n1:section]) &gt; 1">
				<h3>
					<a name="{generate-id($title)}" href="#toc" title="{$locString}">
						<xsl:value-of select="$title"/>
					</a>
				</h3>
			</xsl:when>
			<xsl:otherwise>
				<h3  title="{$locString}">
					<xsl:value-of select="$title"/>
				</h3>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- section author -->
	<xsl:template name="section-author">
		<xsl:if test="n1:author">
			<div style="margin-left : 2em;">
				<b>
					<xsl:call-template name="getLocalizedString">
						<xsl:with-param name="key" select="'sectionAuthor'"/>
						<xsl:with-param name="post" select="': '"/>
					</xsl:call-template>
				</b>
				<div style="margin-left : 3em;">
					<ul>
						<xsl:for-each select="n1:author">
							<li>
								<xsl:choose>
									<xsl:when test="n1:assignedAuthor/n1:assignedPerson/n1:name">
										<xsl:call-template name="show-name">
											<xsl:with-param name="name" select="n1:assignedAuthor/n1:assignedPerson"/>
										</xsl:call-template>
										<xsl:if test="n1:assignedAuthor/n1:representedOrganization">
											<xsl:text>, </xsl:text>
											<xsl:call-template name="show-name">
												<xsl:with-param name="name" select="n1:assignedAuthor/n1:representedOrganization"/>
											</xsl:call-template>
										</xsl:if>
									</xsl:when>
									<xsl:when test="n1:assignedAuthor/n1:assignedAuthoringDevice/n1:softwareName">
										<xsl:value-of select="n1:assignedAuthor/n1:assignedAuthoringDevice/n1:softwareName"/>
									</xsl:when>
									<xsl:otherwise>
										<xsl:for-each select="n1:assignedAuthor/n1:id">
											<br/>
											<xsl:call-template name="getLocalizedString">
												<xsl:with-param name="key" select="'id'"/>
												<xsl:with-param name="post" select="': '"/>
											</xsl:call-template>
											<xsl:call-template name="show-id"/>
										</xsl:for-each>
									</xsl:otherwise>
								</xsl:choose>
							</li>
						</xsl:for-each>
					</ul>
				</div>
			</div>
		</xsl:if>
	</xsl:template>
	<!-- section informant -->
	<xsl:template name="section-informant">
		<xsl:if test="n1:informant">
			<div style="margin-left : 2em;">
				<b>
					<xsl:call-template name="getLocalizedString">
						<xsl:with-param name="key" select="'sectionInformant'"/>
						<xsl:with-param name="post" select="': '"/>
					</xsl:call-template>
				</b>
				<div style="margin-left : 3em;">
					<ul>
					<xsl:for-each select="n1:informant">
						<li>
						<xsl:choose>
							<xsl:when test="n1:relatedEntity">
								<xsl:call-template name="getLocalizedString">
									<xsl:with-param name="pre" select="'('"/>
									<xsl:with-param name="key" select="concat('2.16.840.1.113883.5.110-',n1:relatedEntity/@classCode)"/>
									<xsl:with-param name="post" select="') '"/>
								</xsl:call-template>
								<xsl:call-template name="show-name">
									<xsl:with-param name="name" select="n1:relatedEntity/n1:relatedPerson/n1:name"/>
								</xsl:call-template>
								<xsl:if test="n1:relatedEntity/n1:telecom">
									<xsl:text>, </xsl:text>
									<xsl:call-template name="show-telecom">
										<xsl:with-param name="telecom" select="n1:relatedEntity/n1:telecom"/>
									</xsl:call-template>
								</xsl:if>
							</xsl:when>
							<xsl:when test="n1:assignedEntity">
								<xsl:choose>
									<xsl:when test="n1:assignedEntity/n1:assignedPerson/n1:name">
										<xsl:call-template name="show-name">
											<xsl:with-param name="name" select="n1:assignedEntity/n1:assignedPerson/n1:name"/>
										</xsl:call-template>
										<xsl:if test="n1:assignedEntity/n1:telecom">
											<xsl:text>, </xsl:text>
											<xsl:call-template name="show-telecom">
												<xsl:with-param name="telecom" select="n1:assignedEntity/n1:telecom"/>
											</xsl:call-template>
										</xsl:if>
										<xsl:if test="n1:assignedEntity/n1:representedOrganization">
											<xsl:text>, </xsl:text>
											<xsl:call-template name="show-name">
												<xsl:with-param name="name" select="n1:assignedEntity/n1:representedOrganization/n1:name"/>
											</xsl:call-template>
										</xsl:if>
										<xsl:if test="n1:assignedEntity/n1:representedOrganization/n1:telecom">
											<xsl:text>, </xsl:text>
											<xsl:call-template name="show-telecom">
												<xsl:with-param name="telecom" select="n1:assignedEntity/n1:representedOrganization/n1:telecom"/>
											</xsl:call-template>
										</xsl:if>
									</xsl:when>
									<xsl:otherwise>
										<xsl:for-each select="n1:assignedEntity/n1:id">
											<br/>
											<xsl:call-template name="getLocalizedString">
												<xsl:with-param name="key" select="'id'"/>
												<xsl:with-param name="post" select="': '"/>
											</xsl:call-template>
											<xsl:call-template name="show-id"/>
										</xsl:for-each>
									</xsl:otherwise>
								</xsl:choose>
							</xsl:when>
						</xsl:choose>
						</li>
					</xsl:for-each>
					</ul>
				</div>
			</div>
		</xsl:if>
	</xsl:template>
	<!-- section subject -->
	<xsl:template name="section-subject">
		<xsl:if test="n1:subject">
			<div style="margin-left : 2em;">
				<b>
					<xsl:call-template name="getLocalizedString">
						<xsl:with-param name="key" select="'sectionSubject'"/>
						<xsl:with-param name="post" select="': '"/>
					</xsl:call-template>
				</b>
				
				<!--<subject>
						<relatedSubject>
						<subject>
							<name>Mr. Related Subject</name>
							<administrativeGenderCode code="M" codeSystem="2.16.840.1.113883.5.1"/>
							<birthTime value="19320924"/>
						</subject>
					</relatedSubject>
				</subject>-->
				<div style="margin-left : 3em;">
					<ul>
						<li>
							<xsl:if test="n1:subject/n1:relatedSubject/n1:subject/n1:name">
								<xsl:call-template name="show-name">
									<xsl:with-param name="name" select="n1:subject/n1:relatedSubject/n1:subject/n1:name"/>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="n1:subject/n1:relatedSubject/n1:code">
								<br/>
								<xsl:call-template name="getLocalizedString">
									<xsl:with-param name="key" select="'Type'"/>
									<xsl:with-param name="post" select="': '"/>
								</xsl:call-template>
								<xsl:call-template name="getLocalizedString">
									<xsl:with-param name="key"
										select="concat(n1:subject/n1:relatedSubject/n1:code/@code,'-',n1:subject/n1:relatedSubject/n1:code/@code)"/>
								</xsl:call-template>
							</xsl:if>
							<xsl:if test="n1:subject/n1:relatedSubject/n1:subject/n1:administrativeGenderCode">
								<br/>
								<xsl:call-template name="getLocalizedString">
									<xsl:with-param name="key" select="'administrativeGenderCode'"/>
									<xsl:with-param name="post" select="': '"/>
								</xsl:call-template>
								<xsl:for-each select="n1:subject/n1:relatedSubject/n1:subject/n1:administrativeGenderCode">
									<xsl:call-template name="show-gender"/>
								</xsl:for-each>
							</xsl:if>
							<xsl:if test="n1:subject/n1:relatedSubject/n1:subject/n1:birthTime">
								<br/>
								<xsl:call-template name="getLocalizedString">
									<xsl:with-param name="key" select="'birthTimeLong'"/>
									<xsl:with-param name="post" select="': '"/>
								</xsl:call-template>
								<xsl:call-template name="show-time">
									<xsl:with-param name="datetime" select="n1:subject/n1:relatedSubject/n1:subject/n1:birthTime"/>
								</xsl:call-template>
							</xsl:if>
						</li>
					</ul>
				</div>
			</div>
		</xsl:if>
	</xsl:template>
	<!-- top-level section Text   -->
	<xsl:template name="section-text">
		<div>
			<xsl:apply-templates select="n1:text"/>
		</div>
	</xsl:template>
	<!-- nested component/section -->
	<xsl:template name="nestedSection">
		<xsl:param name="margin"/>
		<h4 style="margin-left : {$margin}em;">
			<xsl:value-of select="n1:title"/>
		</h4>
		<div style="margin-left : {$margin}em;">
			<xsl:apply-templates select="n1:text"/>
		</div>
		<xsl:for-each select="n1:component/n1:section">
			<xsl:call-template name="nestedSection">
				<xsl:with-param name="margin" select="2*$margin"/>
			</xsl:call-template>
		</xsl:for-each>
	</xsl:template>
	<!--   paragraph  -->
	<xsl:template match="n1:paragraph">
		<p>
			<xsl:apply-templates select="." mode="handleStyleCode"/>
			<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
			<xsl:apply-templates/>
		</p>
	</xsl:template>
	<!--   linkHtml  -->
	<xsl:template match="n1:linkHtml">
		<xsl:element name="a">
			<xsl:attribute name="target">
				<xsl:text>_blank</xsl:text>
			</xsl:attribute>
			<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
			<xsl:apply-templates/>
		</xsl:element>
	</xsl:template>
	<!--   pre format  -->
	<xsl:template match="n1:pre">
		<pre>
			<xsl:apply-templates/>
		</pre>
	</xsl:template>
	<!--   Content w/ deleted text is hidden -->
	<!--xsl:template match="n1:content[@revised='delete']"/-->
	<!--   content  -->
	<xsl:template match="n1:content">
		<span>
			<xsl:apply-templates select="." mode="handleStyleCode"/>
			<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
			<xsl:apply-templates/>
		</span>
	</xsl:template>
	<!-- line break -->
	<xsl:template match="n1:br">
		<xsl:element name="br">
			<xsl:apply-templates/>
		</xsl:element>
	</xsl:template>
	<!--   list  -->
	<xsl:template match="n1:list">
		<!-- caption -->
		<xsl:if test="n1:caption">
			<div style="font-weight:bold; ">
				<xsl:apply-templates select="n1:caption"/>
			</div>
		</xsl:if>
		<!-- item -->
		<xsl:choose>
			<xsl:when test="@listType='ordered'">
				<ol>
					<xsl:apply-templates select="." mode="handleStyleCode"/>
					<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
					<xsl:for-each select="n1:item">
						<li>
							<xsl:apply-templates select="." mode="handleStyleCode"/>
							<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
							<xsl:apply-templates/>
						</li>
					</xsl:for-each>
				</ol>
			</xsl:when>
			<xsl:otherwise>
				<!-- list is unordered -->
				<ul>
					<xsl:apply-templates select="." mode="handleStyleCode"/>
					<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
					<xsl:for-each select="n1:item">
						<li>
							<xsl:apply-templates select="." mode="handleStyleCode"/>
							<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
							<xsl:apply-templates/>
						</li>
					</xsl:for-each>
				</ul>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!--   caption  -->
	<xsl:template match="n1:caption">
		<xsl:apply-templates select="." mode="handleStyleCode"/>
		<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
		<xsl:apply-templates/>
		<xsl:text>: </xsl:text>
	</xsl:template>
	<!-- footnote -->
	<xsl:template match="n1:footnote">
		<xsl:variable name="id" select="@ID"/>
		<xsl:variable name="footNoteNum">
			<xsl:for-each select="//n1:footnote">
				<xsl:if test="@ID=$id">
					<xsl:value-of select="position()"/>
				</xsl:if>
			</xsl:for-each>
		</xsl:variable>
		<div class="narr_footnote">
			<xsl:apply-templates select="." mode="handleStyleCode"/>
			<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
			<a name="{@ID}"/>
			<xsl:value-of select="$footNoteNum"/>
			<xsl:text>. </xsl:text>
			<xsl:apply-templates/>
		</div>
	</xsl:template>
	<!-- footnoteRef -->
	<xsl:template match="n1:footnoteRef">
		<xsl:variable name="idref" select="@IDREF"/>
		<xsl:variable name="footNoteNum">
			<xsl:for-each select="//n1:footnote">
				<xsl:if test="@ID=$idref">
					<xsl:value-of select="position()"/>
				</xsl:if>
			</xsl:for-each>
		</xsl:variable>
		<sup>
			<a href="#{$idref}">
				<xsl:attribute name="title">
					<xsl:call-template name="getLocalizedString">
						<xsl:with-param name="key" select="'Footnote'"/>
					</xsl:call-template>
				</xsl:attribute>
				<xsl:value-of select="$footNoteNum"/>
			</a>
		</sup>
	</xsl:template>
	<!--  Tables   -->
	<xsl:template match="n1:table/@*|n1:thead/@*|n1:tfoot/@*|n1:tbody/@*|n1:colgroup/@*|n1:col/@*|n1:tr/@*|n1:th/@*|n1:td/@*">
		<xsl:copy>
			<xsl:copy-of select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>
	<xsl:template match="n1:table">
		<table class="narr_table">
			<xsl:apply-templates select="." mode="handleStyleCode"/>
			<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
			<xsl:apply-templates/>
		</table>
	</xsl:template>
	<xsl:template match="n1:thead">
		<thead>
			<xsl:apply-templates select="." mode="handleStyleCode"/>
			<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
			<xsl:apply-templates/>
		</thead>
	</xsl:template>
	<xsl:template match="n1:tfoot">
		<tfoot>
			<xsl:apply-templates select="." mode="handleStyleCode"/>
			<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
			<xsl:apply-templates/>
		</tfoot>
	</xsl:template>
	<xsl:template match="n1:tbody">
		<tbody>
			<xsl:apply-templates select="." mode="handleStyleCode"/>
			<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
			<xsl:apply-templates/>
		</tbody>
	</xsl:template>
	<xsl:template match="n1:colgroup">
		<colgroup>
			<xsl:apply-templates select="." mode="handleStyleCode"/>
			<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
			<xsl:apply-templates/>
		</colgroup>
	</xsl:template>
	<xsl:template match="n1:col">
		<col>
			<xsl:apply-templates select="." mode="handleStyleCode"/>
			<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
			<xsl:apply-templates/>
		</col>
	</xsl:template>
	<xsl:template match="n1:tr">
		<tr class="narr_tr">
			<xsl:apply-templates select="." mode="handleStyleCode"/>
			<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
			<xsl:apply-templates/>
		</tr>
	</xsl:template>
	<xsl:template match="n1:th">
		<th class="narr_th">
			<xsl:apply-templates select="." mode="handleStyleCode"/>
			<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
			<xsl:apply-templates/>
		</th>
	</xsl:template>
	<xsl:template match="n1:td">
		<td>
			<xsl:apply-templates select="." mode="handleStyleCode"/>
			<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
			<xsl:apply-templates/>
		</td>
	</xsl:template>
	<!-- table/caption -->
	<xsl:template match="n1:table/n1:caption">
		<caption>
			<xsl:apply-templates select="." mode="handleStyleCode"/>
			<xsl:apply-templates select="." mode="handleOtherStyleCodes"/>
			<xsl:apply-templates/>
		</caption>
	</xsl:template>
	<!--   RenderMultiMedia 
    this currently only handles GIF's and JPEG's.  It could, however,
    be extended by including other image MIME types in the predicate
    and/or by generating <object> or <applet> tag with the correct
    params depending on the media type  @ID  =$imageRef  referencedObject
    -->
	<xsl:template match="n1:renderMultiMedia">
		<xsl:variable name="imageRef" select="@referencedObject"/>
		<xsl:choose>
			<xsl:when test="//n1:regionOfInterest[@ID=$imageRef]">
				<!-- Here is where the Region of Interest image referencing goes -->
				<xsl:choose>
					<xsl:when test="//n1:regionOfInterest[@ID=$imageRef]//n1:observationMedia/n1:value[@representation='B64']">
						<xsl:variable name="mediaType"
							select="//n1:regionOfInterest[@ID=$imageRef]//n1:observationMedia/n1:value[@representation]/@mediaType"/>
						<xsl:variable name="string" select="//n1:regionOfInterest[@ID=$imageRef]//n1:observationMedia/n1:value[@representation]"/>

						<xsl:variable name="mediaIdRoot"
							select="//n1:regionOfInterest[@ID=$imageRef]//n1:observationMedia[n1:value/@representation]/n1:id/@root"/>
						<xsl:variable name="mediaIdExt"
							select="//n1:regionOfInterest[@ID=$imageRef]//n1:observationMedia[n1:value/@representation]/n1:id/@extension"/>

						<br clear="all"/>
						<xsl:element name="img">
							<xsl:attribute name="alt">
								<xsl:if test="string-length($mediaIdRoot) or string-length($mediaIdExt)">
									<xsl:value-of select="concat('id = ',$mediaIdRoot,' ',$mediaIdExt)"/>
								</xsl:if>
							</xsl:attribute>
							<xsl:attribute name="title">
								<xsl:if test="string-length($mediaIdRoot) or string-length($mediaIdExt)">
									<xsl:value-of select="concat('id = ',$mediaIdRoot,' ',$mediaIdExt)"/>
								</xsl:if>
							</xsl:attribute>
							<xsl:attribute name="src">
								<xsl:value-of select="concat('data:',$mediaType,';base64,',$string)"/>
							</xsl:attribute>
						</xsl:element>
					</xsl:when>
					<xsl:when test="//n1:regionOfInterest[@ID=$imageRef]//n1:observationMedia/n1:value/n1:reference/@value">

						<xsl:variable name="mediaIdRoot"
							select="//n1:regionOfInterest[@ID=$imageRef]//n1:observationMedia[n1:value/n1:reference]/n1:id/@root"/>
						<xsl:variable name="mediaIdExt"
							select="//n1:regionOfInterest[@ID=$imageRef]//n1:observationMedia[n1:value/n1:reference]/n1:id/@extension"/>

						<br clear="all"/>
						<xsl:element name="img">
							<xsl:attribute name="alt">
								<xsl:if test="string-length($mediaIdRoot) or string-length($mediaIdExt)">
									<xsl:value-of select="concat('id = ',$mediaIdRoot,' ',$mediaIdExt)"/>
								</xsl:if>
							</xsl:attribute>
							<xsl:attribute name="title">
								<xsl:if test="string-length($mediaIdRoot) or string-length($mediaIdExt)">
									<xsl:value-of select="concat('id = ',$mediaIdRoot,' ',$mediaIdExt)"/>
								</xsl:if>
							</xsl:attribute>
							<xsl:attribute name="src">
								<xsl:value-of select="//n1:regionOfInterest[@ID=$imageRef]//n1:observationMedia/n1:value/n1:reference/@value"/>
							</xsl:attribute>
						</xsl:element>
					</xsl:when>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<!-- Here is where the direct MultiMedia image referencing goes -->
				<xsl:if test="//n1:observationMedia[@ID=$imageRef]/n1:value[@mediaType='image/gif' or @mediaType='image/jpeg']">
					<br clear="all"/>
					<xsl:element name="img">
						<xsl:attribute name="alt">
							<xsl:if test="//n1:observationMedia[@ID=$imageRef]/n1:id">
								<xsl:value-of
									select="concat('id = ',//n1:observationMedia[@ID=$imageRef]/n1:id/@root,' ',//n1:observationMedia[@ID=$imageRef]/n1:id/@extension)"
								/>
							</xsl:if>
						</xsl:attribute>
						<xsl:attribute name="src">
							<xsl:value-of select="//n1:observationMedia[@ID=$imageRef]/n1:value/n1:reference/@value"/>
						</xsl:attribute>
					</xsl:element>
				</xsl:if>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<!--    Stylecode processing -->
	<xsl:template match="*" mode="handleStyleCode">
		<xsl:if test="@styleCode">
			<xsl:attribute name="style">
				<xsl:if test="contains(@styleCode,'Bold')">
					<xsl:text>font-weight:bold;</xsl:text>
				</xsl:if>
				<xsl:if test="contains(@styleCode,'Italics')">
					<xsl:text>font-style: italic;</xsl:text>
				</xsl:if>
				<xsl:if test="contains(@styleCode,'Underline')">
					<xsl:text>text-decoration: underline;</xsl:text>
				</xsl:if>
				<xsl:if test="contains(@styleCode,'Emphasis')">
					<xsl:text>font-weight:bold; font-style: italic;</xsl:text>
				</xsl:if>
				<xsl:if test="contains(@styleCode,'Lrule')">
					<xsl:text>border-left-width: 2px; border-left-style: solid;</xsl:text>
				</xsl:if>
				<xsl:if test="contains(@styleCode,'Rrule')">
					<xsl:text>border-right-width: 2px; border-right-style: solid;</xsl:text>
				</xsl:if>
				<xsl:if test="contains(@styleCode,'Toprule')">
					<xsl:text>border-top-width: 2px; border-top-style: solid;</xsl:text>
				</xsl:if>
				<xsl:if test="contains(@styleCode,'Botrule')">
					<xsl:text>border-bottom-width: 2px; border-bottom-style: solid;</xsl:text>
				</xsl:if>
				<xsl:choose>
					<xsl:when test="contains(@styleCode,'Arabic')">
						<xsl:text>list-style: arabic;</xsl:text>
					</xsl:when>
					<xsl:when test="contains(@styleCode,'LittleRoman')">
						<xsl:text>list-style: lower-roman;</xsl:text>
					</xsl:when>
					<xsl:when test="contains(@styleCode,'BigRoman')">
						<xsl:text>list-style: upper-roman;</xsl:text>
					</xsl:when>
					<xsl:when test="contains(@styleCode,'LittleAlpha')">
						<xsl:text>list-style: lower-alpha;</xsl:text>
					</xsl:when>
					<xsl:when test="contains(@styleCode,'BigAlpha')">
						<xsl:text>list-style: upper-alpha</xsl:text>
					</xsl:when>
					<xsl:when test="contains(@styleCode,'Disc')">
						<xsl:text>list-style: disc;</xsl:text>
					</xsl:when>
					<xsl:when test="contains(@styleCode,'Circle')">
						<xsl:text>list-style: circle;</xsl:text>
					</xsl:when>
					<xsl:when test="contains(@styleCode,'Square')">
						<xsl:text>list-style: square;</xsl:text>
					</xsl:when>
				</xsl:choose>
			</xsl:attribute>
		</xsl:if>
	</xsl:template>

	<!-- Other style attribute processing -->
	<xsl:template match="*" mode="handleOtherStyleCodes">
		<!-- General stuff -->
		<xsl:if test="@ID">
			<xsl:attribute name="id">
				<xsl:value-of select="@ID"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@IDREF">
			<xsl:attribute name="idref">
				<xsl:value-of select="@IDREF"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@language">
			<xsl:attribute name="lang">
				<xsl:value-of select="@language"/>
			</xsl:attribute>
		</xsl:if>

		<!-- Table stuff -->
		<xsl:if test="@border">
			<xsl:attribute name="border">
				<xsl:value-of select="@border"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@frame">
			<xsl:attribute name="frame">
				<xsl:value-of select="@frame"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@rules">
			<xsl:attribute name="rules">
				<xsl:value-of select="@rules"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test=". = n1:table">
			<xsl:choose>
				<xsl:when test="@cellpadding">
					<xsl:attribute name="cellpadding">
						<xsl:value-of select="@cellpadding"/>
					</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="cellpadding">
						<xsl:text>1</xsl:text>
					</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:if>
		<xsl:if test=". = n1:table">
			<xsl:choose>
				<xsl:when test="@cellspacing">
					<xsl:attribute name="cellspacing">
						<xsl:value-of select="@cellspacing"/>
					</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="cellspacing">
						<xsl:text>4</xsl:text>
					</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:if>
		<xsl:if test="@span">
			<xsl:attribute name="span">
				<xsl:value-of select="@span"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@summary">
			<xsl:attribute name="summary">
				<xsl:value-of select="@summary"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@width">
			<xsl:attribute name="width">
				<xsl:value-of select="@width"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@align">
			<xsl:attribute name="align">
				<xsl:value-of select="@align"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@valign">
			<xsl:attribute name="valign">
				<xsl:value-of select="@valign"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@char">
			<xsl:attribute name="char">
				<xsl:value-of select="@char"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@charoff">
			<xsl:attribute name="charoff">
				<xsl:value-of select="@charoff"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@abbr">
			<xsl:attribute name="abbr">
				<xsl:value-of select="@abbr"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@scope">
			<xsl:attribute name="scope">
				<xsl:value-of select="@scope"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@headers">
			<xsl:attribute name="headers">
				<xsl:value-of select="@headers"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@axis">
			<xsl:attribute name="axis">
				<xsl:value-of select="@axis"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@colspan">
			<xsl:attribute name="colspan">
				<xsl:value-of select="@colspan"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@rowspan">
			<xsl:attribute name="rowspan">
				<xsl:value-of select="@rowspan"/>
			</xsl:attribute>
		</xsl:if>

		<!-- Text stuff -->
		<xsl:if test="@revised">
			<xsl:attribute name="class">
				<xsl:text>revision_</xsl:text>
				<xsl:value-of select="@revised"/>
				<xsl:text>_final</xsl:text>
			</xsl:attribute>
			<xsl:attribute name="title">
				<xsl:value-of select="@revised"/>
			</xsl:attribute>
		</xsl:if>

		<!-- LinkHTML stuff -->
		<xsl:if test="@name">
			<xsl:attribute name="name">
				<xsl:value-of select="@name"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@rel">
			<xsl:attribute name="rel">
				<xsl:value-of select="@rel"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@href">
			<xsl:attribute name="href">
				<xsl:value-of select="@href"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@title">
			<xsl:attribute name="title">
				<xsl:value-of select="@title"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="@rev">
			<xsl:attribute name="rev">
				<xsl:value-of select="@rev"/>
			</xsl:attribute>
		</xsl:if>
	</xsl:template>

	<!--    Superscript or Subscript   -->
	<xsl:template match="n1:sup">
		<xsl:element name="sup">
			<xsl:apply-templates/>
		</xsl:element>
	</xsl:template>
	<xsl:template match="n1:sub">
		<xsl:element name="sub">
			<xsl:apply-templates/>
		</xsl:element>
	</xsl:template>
	<!-- show-signature -->
	<xsl:template name="show-sig">
		<xsl:param name="sig"/>
		<xsl:call-template name="getLocalizedString">
			<xsl:with-param name="key" select="$sig/@code"/>
		</xsl:call-template>
	</xsl:template>
	<!--	show-id	-->
	<xsl:template name="show-id">
		<xsl:param name="id"/>
		<xsl:choose>
			<xsl:when test="not($id)">
				<xsl:if test="not(@nullFlavor)">
					<xsl:if test="@extension">
						<xsl:value-of select="@extension"/>
						<xsl:text> (</xsl:text>
					</xsl:if>
					<xsl:call-template name="getLocalizedString">
						<xsl:with-param name="key" select="@root"/>
					</xsl:call-template>
					<xsl:if test="@extension">
						<xsl:text>)</xsl:text>
					</xsl:if>
				</xsl:if>
			</xsl:when>
			<xsl:otherwise>
				<xsl:if test="not($id/@nullFlavor)">
					<xsl:if test="$id/@extension">
						<xsl:value-of select="$id/@extension"/>
						<xsl:text> (</xsl:text>
					</xsl:if>
					<xsl:call-template name="getLocalizedString">
						<xsl:with-param name="key" select="$id/@root"/>
					</xsl:call-template>
					<xsl:if test="$id/@extension">
						<xsl:text>)</xsl:text>
					</xsl:if>
				</xsl:if>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- show-name	-->
	<xsl:template name="show-name">
		<xsl:param name="name"/>
		<xsl:choose>
			<xsl:when test="$name">
				<xsl:for-each select="$name">
					<xsl:if test="position() > 1">
						<br/>
					</xsl:if>
					<xsl:if test="@use">
						<xsl:text> </xsl:text>
						<xsl:call-template name="getLocalizedString">
							<xsl:with-param name="key" select="concat('nameUse_',@use)"/>
							<xsl:with-param name="post" select="': '"/>
						</xsl:call-template>
					</xsl:if>
					<xsl:for-each select="node()">
						<!-- Except for prefix, suffix and delimiter name parts, every name part is surrounded by implicit whitespace. 
							Leading and trailing explicit whitespace is insignificant in all those name parts. -->
						<xsl:if test="not(self::n1:prefix or self::n1:suffix or self::n1:delimiter or self::text()) and string-length(normalize-space(.)) > 0">
							<xsl:text> </xsl:text>
						</xsl:if>
						<xsl:choose>
							<xsl:when test="self::n1:family">
								<xsl:call-template name="caseUp">
									<xsl:with-param name="data" select="."/>
								</xsl:call-template>
							</xsl:when>
							<xsl:when test="self::n1:prefix[contains(@qualifier,'VV')]">
								<xsl:call-template name="caseUp">
									<xsl:with-param name="data" select="."/>
								</xsl:call-template>
								<xsl:text> </xsl:text>
							</xsl:when>
							<xsl:when test="self::n1:prefix | self::n1:given">
								<xsl:value-of select="."/>
							</xsl:when>
							<xsl:when test="self::n1:suffix">
								<xsl:value-of select="."/>
							</xsl:when>
							<xsl:otherwise>
								<xsl:if test="not(self::text()) or string-length(normalize-space(.)) > 0">
									<xsl:value-of select="."/>
								</xsl:if>
							</xsl:otherwise>
						</xsl:choose>
						<xsl:if test="not(self::n1:prefix or self::n1:suffix or self::n1:delimiter or self::text()) and string-length(normalize-space(.)) > 0">
							<xsl:text> </xsl:text>
						</xsl:if>
					</xsl:for-each>
				</xsl:for-each>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	<!-- show-gender	-->
	<xsl:template name="show-gender">
		<xsl:call-template name="getLocalizedString">
			<xsl:with-param name="key" select="concat(@codeSystem,'-',@code)"/>
		</xsl:call-template>
	</xsl:template>
	<!-- show-race-ethnicity  -->
	<xsl:template name="show-race-ethnicity">
		<xsl:choose>
			<xsl:when test="@displayName">
				<xsl:value-of select="@displayName"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="@code"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- show-contactInfo -->
	<xsl:template name="show-contactInfo">
		<xsl:param name="contact"/>
		<xsl:call-template name="show-address">
			<xsl:with-param name="address" select="$contact/n1:addr"/>
		</xsl:call-template>
		<br/>
		<xsl:call-template name="show-telecom">
			<xsl:with-param name="telecom" select="$contact/n1:telecom"/>
		</xsl:call-template>
	</xsl:template>
	<!-- show-address	-->
	<xsl:template name="show-address">
		<xsl:param name="address"/>
		<xsl:choose>
			<xsl:when test="$address">
				<xsl:for-each select="$address">
					<xsl:if test="position() > 1">
						<br/>
					</xsl:if>
					<xsl:if test="@use">
						<xsl:call-template name="tokenize">
							<xsl:with-param name="prefix" select="'addressUse_'"/>
							<xsl:with-param name="string" select="@use"/>
							<xsl:with-param name="delimiters" select="' '"/>
						</xsl:call-template>
						<xsl:text>: </xsl:text>
					</xsl:if>
					<xsl:for-each select="node()">
						<xsl:choose>
							<xsl:when test="self::n1:streetName">
								<!-- 
									Look for 
									- streetName, houseNumber|houseNumberNumeric, additionalLocator, houseNumber|houseNumberNumeric or
									- additionalLocator, houseNumber|houseNumberNumeric
									in that order and nothing in between.
								-->
								<xsl:value-of select="."/>
								<xsl:if test="following-sibling::n1:*[1][local-name()='houseNumberNumeric']">
									<xsl:text>&#160;</xsl:text>
									<xsl:value-of select="following-sibling::n1:*[1][local-name()='houseNumberNumeric']"/>
								</xsl:if>
								<xsl:if test="following-sibling::n1:*[1][local-name()='houseNumber']">
									<xsl:text>&#160;</xsl:text>
									<xsl:value-of select="following-sibling::n1:*[1][local-name()='houseNumber']"/>
								</xsl:if>
								<xsl:if test="not(preceding-sibling::n1:*[1][local-name()='houseNumber' or local-name()='houseNumberNumeric'])">
									<xsl:text>&#160;</xsl:text>
									<xsl:value-of select="following-sibling::n1:*[1][local-name()='houseNumber' or local-name()='houseNumberNumeric']/following-sibling::n1:*[1][local-name()='additionalLocator']"/>
									<xsl:if test="following-sibling::n1:*[1][local-name()='houseNumber' or local-name()='houseNumberNumeric']/following-sibling::n1:*[1][local-name()='additionalLocator']">
										<xsl:if
											test="following-sibling::n1:*[1][local-name()='houseNumber' or local-name()='houseNumberNumeric']/following-sibling::n1:*[1][local-name()='additionalLocator']/following-sibling::n1:*[1][local-name()='houseNumberNumeric']">
											<xsl:text>&#160;</xsl:text>
											<xsl:value-of
												select="following-sibling::n1:*[1][local-name()='houseNumber' or local-name()='houseNumberNumeric']/following-sibling::n1:*[1][local-name()='additionalLocator']/following-sibling::n1:*[1][local-name()='houseNumberNumeric']"
											/>
										</xsl:if>
										<xsl:if
											test="following-sibling::n1:*[1][local-name()='houseNumber' or local-name()='houseNumberNumeric']/following-sibling::n1:*[1][local-name()='additionalLocator']/following-sibling::n1:*[1][local-name()='houseNumber']">
											<xsl:text>&#160;</xsl:text>
											<xsl:value-of
												select="following-sibling::n1:*[1][local-name()='houseNumber' or local-name()='houseNumberNumeric']/following-sibling::n1:*[1][local-name()='additionalLocator']/following-sibling::n1:*[1][local-name()='houseNumber']"
											/>
										</xsl:if>
									</xsl:if>
								</xsl:if>
								<xsl:if test="following-sibling::*[not(local-name()='houseNumber')][not(local-name()='houseNumberNumeric')][not(local-name()='additionalLocator')][string-length(.) > 0 or @code]">
									<br/>
								</xsl:if>
							</xsl:when>
							<xsl:when test="self::n1:houseNumber or self::n1:houseNumberNumeric">
								<xsl:if test="not(preceding-sibling::n1:*[1][local-name()='streetName' or local-name()='additionalLocator'])">
									<xsl:value-of select="."/>
									<xsl:if test="(string-length(following-sibling::*) > 0 or following-sibling::*/@code)">
										<br/>
									</xsl:if>
								</xsl:if>
							</xsl:when>
							<xsl:when test="self::n1:additionalLocator">
								<xsl:if test="not(preceding-sibling::n1:*[1][local-name()='houseNumber' or local-name()='houseNumberNumeric'])">
									<xsl:value-of select="."/>
									<xsl:if test="following-sibling::n1:*[1][local-name()='houseNumberNumeric']">
										<xsl:text>&#160;</xsl:text>
										<xsl:value-of select="following-sibling::n1:*[1][local-name()='houseNumberNumeric']"/>
									</xsl:if>
									<xsl:if test="following-sibling::n1:*[1][local-name()='houseNumber']">
										<xsl:text>&#160;</xsl:text>
										<xsl:value-of select="following-sibling::n1:*[1][local-name()='houseNumber']"/>
									</xsl:if>
									<xsl:if test="(string-length(following-sibling::*) > 0 or following-sibling::*/@code)">
										<br/>
									</xsl:if>
								</xsl:if>
							</xsl:when>
							<xsl:when test=". = n1:postBox">
								<xsl:call-template name="getLocalizedString">
									<xsl:with-param name="key" select="'Postbox'"/>
									<xsl:with-param name="post" select="' '"/>
								</xsl:call-template>
								<xsl:value-of select="."/>
								<xsl:if test="(string-length(following-sibling::*) > 0 or following-sibling::*/@code)">
									<br/>
								</xsl:if>
							</xsl:when>
							<xsl:when test="self::n1:city">
								<xsl:value-of select="."/>
								<xsl:if test="../n1:state">
									<xsl:text>, </xsl:text>
									<xsl:value-of select="../n1:state"/>
								</xsl:if>
								<xsl:if test="(string-length(following-sibling::*) > 0 or following-sibling::*/@code)">
									<br/>
								</xsl:if>
							</xsl:when>
							<xsl:when test="self::n1:state and not(../n1:city)">
								<xsl:if test="(string-length(preceding-sibling::*) > 0 or preceding-sibling::*/@code)">
									<br/>
								</xsl:if>
								<xsl:value-of select="."/>
								<xsl:if test="(string-length(following-sibling::*) > 0 or following-sibling::*/@code)">
									<br/>
								</xsl:if>
							</xsl:when>
							<xsl:when test="self:: n1:state"/>
							<xsl:otherwise>
								<xsl:if test="string-length(./text()) > 0">
									<xsl:value-of select="."/>
									<xsl:if test="(string-length(following-sibling::*) > 0 or following-sibling::*/@code)">
										<br/>
									</xsl:if>
								</xsl:if>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:for-each>
				</xsl:for-each>
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'address not available'"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- show-telecom	-->
	<xsl:template name="show-telecom">
		<xsl:param name="telecom"/>
		<xsl:choose>
			<xsl:when test="$telecom">
				<xsl:for-each select="$telecom">
					<xsl:if test="position() > 1">
						<br/>
					</xsl:if>

					<xsl:variable name="type" select="substring-before(@value, ':')"/>
					<xsl:variable name="value" select="substring-after(@value, ':')"/>
					<xsl:if test="$type">
						<xsl:call-template name="translateTelecomUriScheme">
							<xsl:with-param name="code" select="$type"/>
						</xsl:call-template>
					</xsl:if>
					<xsl:if test="@use">
						<xsl:text> </xsl:text>
						<xsl:call-template name="tokenize">
							<xsl:with-param name="prefix" select="'addressUse_'"/>
							<xsl:with-param name="string" select="@use"/>
							<xsl:with-param name="delimiters" select="' '"/>
						</xsl:call-template>
					</xsl:if>
					<xsl:if test="$type or @use">
						<xsl:text>: </xsl:text>
					</xsl:if>
					<xsl:choose>
						<xsl:when test="$type">
							<xsl:value-of select="$value"/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="@value"/>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:for-each>
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'telecom information not available'"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- show-recipientType -->
	<xsl:template name="show-recipientType">
		<xsl:param name="typeCode"/>
		<xsl:choose>
			<xsl:when test="$typeCode='PRCP' or $typeCode='TRC'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="concat('typeCode-',$typeCode)"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'Recipient'"/>
					<xsl:with-param name="post" select="':'"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- Convert Telecom URL to display text -->
	<xsl:template name="translateTelecomUriScheme">
		<xsl:param name="code"/>
		<!--xsl:value-of select="document('voc.xml')/systems/system[@root=$code/@codeSystem]/code[@value=$code/@code]/@displayName"/-->
		<!--xsl:value-of select="document('codes.xml')/*/code[@code=$code]/@display"/-->
		<xsl:choose>
			<!-- lookup table Telecom URI -->
			<xsl:when test="$code='tel'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'Tel'"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$code='fax'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'Fax'"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$code='http' or $code='https'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'Web'"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$code='mailto'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'Mail'"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>{$code='</xsl:text>
				<xsl:value-of select="$code"/>
				<xsl:text>'?}</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- Convert telecom/@use or addr/@use -->
	<xsl:template name="translateTelecomCode">
		<xsl:param name="code"/>
		<!-- once XSLT 2 is possible, most of this weirdness maybe done through real tokenize... -->
		<xsl:variable name="codeText">
			<xsl:call-template name="tokenize">
				<xsl:with-param name="prefix" select="'addressUse_'"/>
				<xsl:with-param name="delimiters" select="' '"/>
				<xsl:with-param name="string" select="$code"/>
			</xsl:call-template>
		</xsl:variable>
		<xsl:value-of select="normalize-space($codeText)"/>
	</xsl:template>
	<!-- convert RoleClassAssociative code to display text -->
	<xsl:template name="translateRoleAssoCode">
		<xsl:param name="classCode"/>
		<xsl:param name="code"/>
		<xsl:call-template name="getLocalizedString">
			<xsl:with-param name="key" select="concat('2.16.840.1.113883.5.110-',$classCode)"/>
		</xsl:call-template>
		<xsl:if test="$code/@code">
			<xsl:text> </xsl:text>
			<xsl:variable name="codeDisplayName">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="concat($code/@codeSystem,'-',$code/@code)"/>
				</xsl:call-template>
			</xsl:variable>
			<xsl:choose>
				<xsl:when test="not($codeDisplayName=concat($code/@codeSystem,'-',$code/@code))">
					<xsl:value-of select="concat('(',$codeDisplayName,')')"/>
				</xsl:when>
				<xsl:when test="$code/@displayName">
					<xsl:value-of select="concat('(',$code/@displayName,')')"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="concat('(',$code/@code,')')"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:if>
	</xsl:template>
	<!-- show time -->
	<xsl:template name="show-time">
		<xsl:param name="datetime"/>
		<xsl:choose>
			<xsl:when test="not($datetime)">
				<xsl:call-template name="formatDateTime">
					<xsl:with-param name="date" select="@value"/>
				</xsl:call-template>
				<xsl:text> </xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="formatDateTime">
					<xsl:with-param name="date" select="$datetime/@value"/>
				</xsl:call-template>
				<xsl:text> </xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- participant facility and date -->
	<xsl:template name="facilityAndDates">
		<table class="header_table">
			<tbody>
				<!-- facility id -->
				<tr>
					<td width="20%" bgcolor="#3399ff">
						<span class="td_label">
							<xsl:call-template name="getLocalizedString">
								<xsl:with-param name="key" select="'Facility ID'"/>
							</xsl:call-template>
						</span>
					</td>
					<td colspan="3">
						<xsl:choose>
							<xsl:when test="count(/n1:ClinicalDocument/n1:participant
                                      [@typeCode='LOC'][@contextControlCode='OP']
                                      /n1:associatedEntity[@classCode='SDLOC']/n1:id)&gt;0">
								<!-- change context node -->
								<xsl:for-each select="/n1:ClinicalDocument/n1:participant
                                      [@typeCode='LOC'][@contextControlCode='OP']
                                      /n1:associatedEntity[@classCode='SDLOC']/n1:id">
									<xsl:call-template name="show-id"/>
									<!-- change context node again, for the code -->
									<xsl:for-each select="../n1:code">
										<xsl:text> (</xsl:text>
										<xsl:call-template name="show-code">
											<xsl:with-param name="code" select="."/>
										</xsl:call-template>
										<xsl:text>)</xsl:text>
									</xsl:for-each>
								</xsl:for-each>
							</xsl:when>
							<xsl:otherwise>
								<xsl:call-template name="getLocalizedString">
									<xsl:with-param name="key" select="'Not available'"/>
								</xsl:call-template>
							</xsl:otherwise>
						</xsl:choose>
					</td>
				</tr>
				<!-- Period reported -->
				<tr>
					<td width="20%" bgcolor="#3399ff">
						<span class="td_label">
							<xsl:call-template name="getLocalizedString">
								<xsl:with-param name="key" select="'First day of period reported'"/>
							</xsl:call-template>
						</span>
					</td>
					<td colspan="3">
						<xsl:call-template name="show-time">
							<xsl:with-param name="datetime" select="/n1:ClinicalDocument/n1:documentationOf
								/n1:serviceEvent/n1:effectiveTime/n1:low"/>
						</xsl:call-template>
					</td>
				</tr>
				<tr>
					<td width="20%" bgcolor="#3399ff">
						<span class="td_label">
							<xsl:call-template name="getLocalizedString">
								<xsl:with-param name="key" select="'Last day of period reported'"/>
							</xsl:call-template>
						</span>
					</td>
					<td colspan="3">
						<xsl:call-template name="show-time">
							<xsl:with-param name="datetime" select="/n1:ClinicalDocument/n1:documentationOf
								/n1:serviceEvent/n1:effectiveTime/n1:high"/>
						</xsl:call-template>
					</td>
				</tr>
			</tbody>
		</table>
	</xsl:template>
	<!-- show assignedEntity -->
	<xsl:template name="show-assignedEntity">
		<xsl:param name="asgnEntity"/>
		<xsl:choose>
			<xsl:when test="$asgnEntity/n1:assignedPerson/n1:name">
				<xsl:call-template name="show-name">
					<xsl:with-param name="name" select="$asgnEntity/n1:assignedPerson/n1:name"/>
				</xsl:call-template>
				<xsl:if test="$asgnEntity/n1:representedOrganization/n1:name">
					<xsl:call-template name="getLocalizedString">
						<xsl:with-param name="pre" select="' '"/>
						<xsl:with-param name="key" select="'of'"/>
						<xsl:with-param name="post" select="' '"/>
					</xsl:call-template>
					<xsl:value-of select="$asgnEntity/n1:representedOrganization/n1:name"/>
				</xsl:if>
			</xsl:when>
			<xsl:when test="$asgnEntity/n1:representedOrganization">
				<xsl:value-of select="$asgnEntity/n1:representedOrganization/n1:name"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:for-each select="$asgnEntity/n1:id">
					<xsl:call-template name="show-id"/>
					<xsl:choose>
						<xsl:when test="position()!=last()">
							<xsl:text>, </xsl:text>
						</xsl:when>
						<xsl:otherwise>
							<br/>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:for-each>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- show relatedEntity -->
	<xsl:template name="show-relatedEntity">
		<xsl:param name="relatedEntity"/>
		<xsl:choose>
			<xsl:when test="$relatedEntity/n1:relatedPerson/n1:name">
				<xsl:call-template name="show-name">
					<xsl:with-param name="name" select="$relatedEntity/n1:relatedPerson/n1:name"/>
				</xsl:call-template>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	<!-- show associatedEntity -->
	<xsl:template name="show-associatedEntity">
		<xsl:param name="assoEntity"/>
		<xsl:if test="$assoEntity/n1:associatedPerson">
			<xsl:for-each select="$assoEntity/n1:associatedPerson/n1:name">
				<xsl:call-template name="show-name">
					<xsl:with-param name="name" select="."/>
				</xsl:call-template>
				<xsl:if test="position()!=last()">
					<br/>
				</xsl:if>
			</xsl:for-each>
		</xsl:if>
		<xsl:if test="$assoEntity/n1:code">
			<xsl:if test="$assoEntity/n1:associatedPerson/n1:name or $assoEntity/n1:associatedPerson/n1:id">
				<xsl:text>, </xsl:text>
			</xsl:if>
			<xsl:call-template name="show-code">
				<xsl:with-param name="code" select="$assoEntity/n1:code"/>
			</xsl:call-template>
		</xsl:if>
		<xsl:if test="$assoEntity/n1:id">
			<xsl:if test="$assoEntity/n1:associatedPerson/n1:name">
				<xsl:text>, </xsl:text>
			</xsl:if>
			<xsl:call-template name="getLocalizedString">
				<xsl:with-param name="key" select="'id'"/>
				<xsl:with-param name="post" select="': '"/>
			</xsl:call-template>
			<xsl:for-each select="$assoEntity/n1:id">
				<xsl:call-template name="show-id"/>
				<xsl:if test="position()!=last()">
					<br/>
				</xsl:if>
			</xsl:for-each>
		</xsl:if>
		<xsl:if test="$assoEntity/n1:scopingOrganization">
			<br/>
			<xsl:call-template name="getLocalizedString">
				<xsl:with-param name="key" select="'organization'"/>
				<xsl:with-param name="post" select="': '"/>
			</xsl:call-template>
		</xsl:if>
		<xsl:choose>
			<xsl:when test="$assoEntity/n1:scopingOrganization/n1:name">
				<xsl:call-template name="show-name">
					<xsl:with-param name="name" select="$assoEntity/n1:scopingOrganization/n1:name"/>
				</xsl:call-template>
				<xsl:if test="$assoEntity/n1:scopingOrganization/n1:standardIndustryClassCode">
					<xsl:value-of select="$assoEntity/n1:scopingOrganization/n1:standardIndustryClassCode/@displayName"/>
					<xsl:call-template name="getLocalizedString">
						<xsl:with-param name="pre" select="' '"/>
						<xsl:with-param name="key" select="'code'"/>
						<xsl:with-param name="post" select="':'"/>
					</xsl:call-template>
					<xsl:value-of select="$assoEntity/n1:scopingOrganization/n1:standardIndustryClassCode/@code"/>
				</xsl:if>
				<xsl:text>, </xsl:text>
			</xsl:when>
			<xsl:when test="$assoEntity/n1:scopingOrganization/n1:standardIndustryClassCode">
				<xsl:value-of select="$assoEntity/n1:scopingOrganization/n1:standardIndustryClassCode/@displayName"/>
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="pre" select="' '"/>
					<xsl:with-param name="key" select="'code'"/>
					<xsl:with-param name="post" select="':'"/>
				</xsl:call-template>
				<xsl:value-of select="$assoEntity/n1:scopingOrganization/n1:standardIndustryClassCode/@code"/>
				<xsl:text>, </xsl:text>
			</xsl:when>
		</xsl:choose>
		<xsl:if test="$assoEntity/n1:scopingOrganization/n1:id">
			<xsl:call-template name="getLocalizedString">
				<xsl:with-param name="key" select="'id'"/>
				<xsl:with-param name="post" select="': '"/>
			</xsl:call-template>
			<xsl:for-each select="$assoEntity/n1:scopingOrganization/n1:id">
				<xsl:call-template name="show-id"/>
				<xsl:if test="position()!=last()">
					<br/>
				</xsl:if>
			</xsl:for-each>
		</xsl:if>
	</xsl:template>
	<!-- show code 
		if originalText present, return it, otherwise, check and return attribute: display name
    -->
	<xsl:template name="show-code">
		<xsl:param name="code"/>
		<xsl:variable name="this-codeSystem">
			<xsl:call-template name="getLocalizedString">
				<xsl:with-param name="key" select="$code/@codeSystem"/>
			</xsl:call-template>
		</xsl:variable>
		<xsl:variable name="this-code">
			<xsl:call-template name="getLocalizedString">
				<xsl:with-param name="key" select="concat($code/@codeSystem,'-',$code/@code)"/>
			</xsl:call-template>
		</xsl:variable>
		<xsl:choose>
			<xsl:when test="$code/n1:originalText">
				<xsl:value-of select="$code/n1:originalText"/>
			</xsl:when>
			<xsl:when test="$code/@displayName">
				<xsl:value-of select="$code/@displayName"/>
			</xsl:when>
			<xsl:when test="not($this-code=concat($code/@codeSystem,'-',$code/@code))">
				<xsl:value-of select="$this-code"/>
			</xsl:when>
			<!--
			<xsl:when test="$the-valuesets/*/voc:system[@root=$this-codeSystem]/voc:code[@value=$this-code]/@displayName">
				<xsl:value-of select="$the-valuesets/*/voc:system[@root=$this-codeSystem]/voc:code[@value=$this-code]/@displayName"/>
			</xsl:when>
			-->
			<xsl:otherwise>
				<xsl:value-of select="$code/@code"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- show classCode -->
	<xsl:template name="show-actClassCode">
		<xsl:param name="clsCode"/>
		<xsl:call-template name="getLocalizedString">
			<xsl:with-param name="key" select="concat('2.16.840.1.113883.5.6-',$clsCode)"/>
		</xsl:call-template>
	</xsl:template>
	<!-- show participationType -->
	<xsl:template name="show-participationType">
		<xsl:param name="ptype"/>
		<xsl:call-template name="getLocalizedString">
			<xsl:with-param name="key" select="concat('2.16.840.1.113883.5.90-',$ptype)"/>
		</xsl:call-template>
	</xsl:template>
	<!-- show participationFunction -->
	<xsl:template name="show-participationFunction">
		<xsl:param name="pFunction"/>
		<xsl:choose>
			<!-- From the HL7 v2 Provider Role code system (2.16.840.1.113883.12.443) which is used by HITSP -->
			<xsl:when test=" $pFunction = 'CP' ">
				<xsl:text>(consulting provider)</xsl:text>
			</xsl:when>
			<xsl:when test=" $pFunction = 'PP' ">
				<xsl:text>(primary care provider)</xsl:text>
			</xsl:when>
			<xsl:when test=" $pFunction = 'RP' ">
				<xsl:text>(referring provider)</xsl:text>
			</xsl:when>
			<xsl:when test=" $pFunction = 'MP' ">
				<xsl:text>(medical home provider)</xsl:text>
			</xsl:when>
			<xsl:otherwise>
				<!-- From the HL7 v3 ParticipationFunction code system -->
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="pre" select="'('"/>
					<xsl:with-param name="key" select="concat('2.16.840.1.113883.5.88-',$pFunction)"/>
					<xsl:with-param name="post" select="')'"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- formatMonth -->
	<xsl:template name="formatMonth">
		<xsl:param name="date"/>
		<!-- month -->
		<xsl:variable name="month" select="substring ($date, 5, 2)"/>
		<xsl:choose>
			<xsl:when test="$month='01'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'January'"/>
					<xsl:with-param name="post" select="' '"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$month='02'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'February'"/>
					<xsl:with-param name="post" select="' '"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$month='03'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'March'"/>
					<xsl:with-param name="post" select="' '"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$month='04'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'April'"/>
					<xsl:with-param name="post" select="' '"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$month='05'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'May'"/>
					<xsl:with-param name="post" select="' '"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$month='06'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'June'"/>
					<xsl:with-param name="post" select="' '"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$month='07'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'July'"/>
					<xsl:with-param name="post" select="' '"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$month='08'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'August'"/>
					<xsl:with-param name="post" select="' '"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$month='09'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'September'"/>
					<xsl:with-param name="post" select="' '"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$month='10'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'October'"/>
					<xsl:with-param name="post" select="' '"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$month='11'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'November'"/>
					<xsl:with-param name="post" select="' '"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$month='12'">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="'December'"/>
					<xsl:with-param name="post" select="' '"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$month"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- formatDateTime -->
	<xsl:template name="formatDateTime">
		<xsl:param name="date"/>
		<xsl:variable name="yearNum" select="substring ($date, 1, 4)"/>
		<xsl:variable name="monthNum" select="substring ($date, 5, 2)"/>
		<xsl:variable name="monthText">
			<xsl:call-template name="formatMonth">
				<xsl:with-param name="date" select="$date"/>
			</xsl:call-template>
		</xsl:variable>
		<xsl:variable name="dayNum">
			<xsl:choose>
				<xsl:when test="substring ($date, 7, 1)=&quot;0&quot;">
					<xsl:value-of select="substring ($date, 8, 1)"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="substring ($date, 7, 2)"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>

		<xsl:choose>
			<xsl:when test="$textLang='nl-NL'">
				<xsl:value-of select="$dayNum"/>
				<xsl:text> </xsl:text>
				<xsl:call-template name="caseDown">
					<xsl:with-param name="data" select="$monthText"/>
				</xsl:call-template>
				<xsl:text> </xsl:text>
				<xsl:value-of select="$yearNum"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="firstCharCaseUp">
					<xsl:with-param name="data" select="$monthText"/>
				</xsl:call-template>
				<xsl:text> </xsl:text>
				<xsl:value-of select="$dayNum"/>
				<xsl:text>, </xsl:text>
				<xsl:value-of select="$yearNum"/>
			</xsl:otherwise>
		</xsl:choose>


		<!-- time and US timezone -->
		<xsl:if test="string-length($date) > 8">
			<xsl:text>, </xsl:text>
			<!-- time -->
			<xsl:variable name="time">
				<xsl:value-of select="substring($date,9,6)"/>
			</xsl:variable>
			<xsl:variable name="hh">
				<xsl:value-of select="substring($time,1,2)"/>
			</xsl:variable>
			<xsl:variable name="mm">
				<xsl:value-of select="substring($time,3,2)"/>
			</xsl:variable>
			<xsl:variable name="ss">
				<xsl:value-of select="substring($time,5,2)"/>
			</xsl:variable>
			<xsl:if test="string-length($hh)&gt;1">
				<xsl:value-of select="$hh"/>
				<xsl:if test="string-length($mm)&gt;1 and not(contains($mm,'-')) and not (contains($mm,'+'))">
					<xsl:text>:</xsl:text>
					<xsl:value-of select="$mm"/>
					<xsl:if test="string-length($ss)&gt;1 and not(contains($ss,'-')) and not (contains($ss,'+'))">
						<xsl:text>:</xsl:text>
						<xsl:value-of select="$ss"/>
					</xsl:if>
				</xsl:if>
			</xsl:if>
			<!-- time zone -->
			<xsl:variable name="tzon">
				<xsl:choose>
					<xsl:when test="contains($date,'+')">
						<xsl:text>+</xsl:text>
						<xsl:value-of select="substring-after($date, '+')"/>
					</xsl:when>
					<xsl:when test="contains($date,'-')">
						<xsl:text>-</xsl:text>
						<xsl:value-of select="substring-after($date, '-')"/>
					</xsl:when>
				</xsl:choose>
			</xsl:variable>
			<xsl:choose>
				<!-- reference: http://www.timeanddate.com/library/abbreviations/timezones/na/ -->
				<xsl:when test="$tzon = '+0200' ">
					<xsl:text>, CEST</xsl:text>
				</xsl:when>
				<xsl:when test="$tzon = '+0100' ">
					<xsl:text>, CET</xsl:text>
				</xsl:when>
				<xsl:when test="$tzon = '-0500' ">
					<xsl:text>, EST</xsl:text>
				</xsl:when>
				<xsl:when test="$tzon = '-0600' ">
					<xsl:text>, CST</xsl:text>
				</xsl:when>
				<xsl:when test="$tzon = '-0700' ">
					<xsl:text>, MST</xsl:text>
				</xsl:when>
				<xsl:when test="$tzon = '-0800' ">
					<xsl:text>, PST</xsl:text>
				</xsl:when>
				<xsl:otherwise>
					<xsl:text> </xsl:text>
					<xsl:value-of select="$tzon"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:if>
	</xsl:template>
	<!-- convert to lower case -->
	<xsl:template name="caseDown">
		<xsl:param name="data"/>
		<xsl:if test="$data">
			<xsl:value-of select="translate($data, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')"/>
		</xsl:if>
	</xsl:template>
	<!-- convert to upper case -->
	<xsl:template name="caseUp">
		<xsl:param name="data"/>
		<xsl:if test="$data">
			<xsl:value-of select="translate($data,'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ')"/>
		</xsl:if>
	</xsl:template>
	<!-- convert first character to upper case -->
	<xsl:template name="firstCharCaseUp">
		<xsl:param name="data"/>
		<xsl:if test="$data">
			<xsl:call-template name="caseUp">
				<xsl:with-param name="data" select="substring($data,1,1)"/>
			</xsl:call-template>
			<xsl:value-of select="substring($data,2)"/>
		</xsl:if>
	</xsl:template>
	<!-- show-noneFlavor -->
	<!-- to do list -->
	<xsl:template name="show-noneFlavor">
		<xsl:param name="nf"/>
		<xsl:call-template name="getLocalizedString">
			<xsl:with-param name="key" select="concat('nullFlavor_',$nf)"/>
		</xsl:call-template>
	</xsl:template>
	<!-- addCss -->
	<xsl:template name="addCSS">
		<xsl:comment>main css</xsl:comment>
		<style type="text/css">
			body {
				color: #003366;
				background-color: #ffffff;
				font-family: Verdana, Tahoma, sans-serif;
				font-size: 11px;
			}
			
			a {
				color: #003366;
				background-color: #ffffff;
			}
			
			h1 {
				font-size: 12pt;
				font-weight: bold;
			}
			
			h2 {
				font-size: 11pt;
				font-weight: bold;
			}
			
			h3 {
				font-size: 10pt;
				font-weight: bold;
			}
			
			h4 {
				font-size: 8pt;
				font-weight: bold;
			}
			
			div {
				width: 95%;
			}
			
			hr {
				width: 100%;
			}
			
			table {
				line-height: 10pt;
				width: 100%;
			}
			
			tr {
				background-color: #ccccff;
			}
			
			td {
				padding: 0.1cm 0.2cm;
				vertical-align: top;
			}
			
			.h1center {
				font-size: 11pt;
				font-weight: bold;
				text-align: center;
			}
			
			.header_table {
				border: 1pt inset #00008b;
			}
			
			.narr_table {
				width: 100%;
			}
			
			.narr_tr {
				background-color: #ffffcc;
			}
			
			.narr_th {
				background-color: #ffd700;
			}
			
			.narr_footnote {
			    font-size: 9px;
			    font-style: italic;
			}
			
			.td_label {
				font-weight: bold;
				color: white;
			}
			
			.revision_insert {
			    text-decoration : underline overline;
			}
			
			.revision_insert_final {
			}
			
			.revision_delete {
			    text-decoration : line-through;
			}
			
			.revision_delete_final {
			    display: none;
			}
			
			.span_button {
				display: table-cell;
				cursor: pointer;
				border: 2pt inset #00008b;
				border-radius: 15px;
				-moz-border-radius: 15px;
				padding: 0.1cm 0.2cm;
				background-color: #ccccff;
				font-weight: bold;
				vertical-align: baseline;
				width: 150px;
			}
		</style>
		<xsl:comment>div layout css</xsl:comment>
		<style type="text/css" media="screen">
			/* fixed header and main body */
			#contentheader {
			    position : absolute;
			    top : 0;
			    left : 0;
			    height : <xsl:value-of select="$contentHeaderHeight"/>; /*Height of frame div*/
				width : <xsl:value-of select="$contentHeaderWidth"/>;
			    overflow : auto; /*Disable scrollbars. Set to "scroll" to enable*/
			}
			#contentmain {
			    position : fixed;
			    top : <xsl:value-of select="$contentHeaderHeight"/>; /*Set top value to HeightOfFrameDiv*/
			    left : 0;
			    right : 0;
			    bottom : 0;
			    width : <xsl:value-of select="$contentMainWidth"/>;
			    overflow : auto;
			    background : #fff;
			}
			.innertube {
			    margin : 15px; /*Margins for inner DIV inside each DIV (to provide padding)*/
			}
			* html body { /*IE6 hack*/
			    padding : <xsl:value-of select="$contentHeaderHeight"/> 0 0 0; /*Set value to (HeightOfFrameDiv 0 0 0)*/
			}
			* html #contentmain { /*IE6 hack*/
			    height : 100%;
			    width : 100%;
			}
		</style>
		<xsl:comment>revision toggle css</xsl:comment>
		<style type="text/css" media="print">
			#revisionToggleOn, #revisionToggleOff {
				display: none;
			}
		</style>
		<xsl:comment>table of contents css</xsl:comment>
		<style type="text/css" media="screen">
            #nav, #nav ul {
                padding: 0;
                margin: 0;
                list-style: none;
            }
            
            #nav li {
                float: left;
                width: 150px;
            }
            
            #nav ul {
                position: absolute;
                width: 150px;
                left: -1000px;
            }
            
            #nav li:hover ul, #nav li.ie_does_hover ul {
                left: auto;
                background-position: 0 0;
            }
            
            #nav * a {
                display: block;
                padding: 2px 8px;
                text-decoration: none;
                font: bold 11px Verdana;
            }
            
            #nav ul * a {
                font-weight: bold;
                color: black;
                background-color: #ccccff;
                cursor: pointer;
            }
            
            #nav ul ul a:link, #nav ul ul a:visited {
                font-weight: normal;
                color: black;
                background-color: #ccccff;
                cursor: pointer;
            }
            
            #nav * li a:hover, #nav * li a:active {
               font-weight: normal;
               color: white;
               background-color: #3399ff;
               cursor: pointer;
            }
            
            #nav * li {
               border-left: 2px solid white;
            }
            
            #nav * ul li {
               border-top: 2px solid white;
               border-left: 0;
            }
            
            /* IE only hack \*/
            * html ul li, * html ul ul li{
                border-bottom: 2px solid white;
            }
            
            * html ul ul li{
                border-top: 0;
            }
            /* Einde IE only hack */
         </style>
	</xsl:template>
	<!-- addJS -->
	<xsl:template name="addJS">
		<xsl:comment>Javascript for Revisions switch</xsl:comment>
		<script type="text/javascript">
			function showReviewMarks() {
				var allHTMLTags=document.getElementsByTagName("*");
				for (i in allHTMLTags) {
					//Get all tags with the specified class name.
					if (allHTMLTags[i].className=='revision_insert_final') {
						allHTMLTags[i].className = 'revision_insert';
					}
					if (allHTMLTags[i].className=='revision_delete_final') {
						allHTMLTags[i].className = 'revision_delete';
					}
				}
				toggle('revisionToggleOn');
				toggle('revisionToggleOff');
			}
			function hideReviewMarks() {
				var allHTMLTags=document.getElementsByTagName("*");
				for (i in allHTMLTags) {
					//Get all tags with the specified class name.
					if (allHTMLTags[i].className=='revision_insert') {
						allHTMLTags[i].className = 'revision_insert_final';
					}
					if (allHTMLTags[i].className=='revision_delete') {
						allHTMLTags[i].className = 'revision_delete_final';
					}
				}
				toggle('revisionToggleOn');
				toggle('revisionToggleOff');
			}
			function toggle(obj) {
				var el = document.getElementById(obj);
				el.style.display = (el.style.display != 'none' ? 'none' : '' );
			}
		</script>
		<xsl:comment>Javascript for Table of Contents menu</xsl:comment>
		<script language="javascript" type="text/javascript">
            sfHover = function() {
            var sfEls = document.getElementById("nav").getElementsByTagName("li");
            for (i in sfEls) {
                  sfEls[i].onmouseover=function() {
                     this.className+=" ie_does_hover";
                  }
                  sfEls[i].onmouseout=function() {
                     this.className=this.className.replace(new RegExp(" ie_does_hover"), "");
                  }
               }
            }
            if (window.attachEvent) window.attachEvent("onload", sfHover);
         </script>
	</xsl:template>
	<!-- getLocalizedString -->
	<xsl:template name="getLocalizedString">
		<xsl:param name="pre" select="''"/>
		<xsl:param name="key"/>
		<xsl:param name="post" select="''"/>
		
		<xsl:choose>
			<!-- compare 'de-CH' -->
			<xsl:when test="$vocMessages/*/*/key[@value=$key]/value[@lang=$textLangLowerCase]">
				<xsl:value-of select="concat($pre,$vocMessages//key[@value=$key]/value[@lang=$textLangLowerCase]/text(),$post)"/>
			</xsl:when>
			<!-- compare 'de' in 'de-CH' -->
			<xsl:when test="$vocMessages/*/*/key[@value=$key]/value[substring(@lang, 1, 2)=$textLangPartLowerCase]">
				<xsl:value-of select="concat($pre,$vocMessages//key[@value=$key]/value[substring(@lang, 1, 2)=$textLangPartLowerCase]/text(),$post)"/>
			</xsl:when>
			<!-- compare 'en-US' -->
			<xsl:when test="$vocMessages/*/*/key[@value=$key]/value[@lang=$textLangDefaultLowerCase]">
				<xsl:value-of select="concat($pre,$vocMessages//key[@value=$key]/value[@lang=$textLangDefaultLowerCase]/text(),$post)"/>
			</xsl:when>
			<!-- compare 'en' in 'en-US' -->
			<xsl:when test="$vocMessages/*/*/key[@value=$key]/value[substring(@lang, 1, 2)=$textLangDefaultPartLowerCase]">
				<xsl:value-of select="concat($pre,$vocMessages//key[@value=$key]/value[substring(@lang, 1, 2)=$textLangDefaultPartLowerCase]/text(),$post)"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="concat($pre,$key,$post)"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!-- tokenize -->
	<xsl:template name="tokenize">
		<xsl:param name="string" select="''" />
		<xsl:param name="delimiters" select="' '" />
		<xsl:param name="prefix"/>
		<xsl:choose>
			<xsl:when test="not($string)" />
			<xsl:when test="not($delimiters)">
				<xsl:call-template name="_tokenize-characters">
					<xsl:with-param name="string" select="$string" />
					<xsl:with-param name="prefix" select="$prefix"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="_tokenize-delimiters">
					<xsl:with-param name="string" select="$string" />
					<xsl:with-param name="delimiters" select="$delimiters" />
					<xsl:with-param name="prefix" select="$prefix"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="_tokenize-characters">
		<xsl:param name="string" />
		<xsl:param name="prefix"/>
		<xsl:if test="$string">
			<xsl:call-template name="getLocalizedString">
				<xsl:with-param name="key" select="concat($prefix,substring($string, 1, 1))"/>
			</xsl:call-template>
			<xsl:call-template name="_tokenize-characters">
				<xsl:with-param name="string" select="substring($string, 2)" />
				<xsl:with-param name="prefix" select="$prefix"/>
			</xsl:call-template>
		</xsl:if>
	</xsl:template>
	<xsl:template name="_tokenize-delimiters">
		<xsl:param name="string" />
		<xsl:param name="delimiters" />
		<xsl:param name="prefix"/>
		<xsl:variable name="delimiter" select="substring($delimiters, 1, 1)" />
		<xsl:choose>
			<xsl:when test="not($delimiter)">
				<xsl:call-template name="getLocalizedString">
					<xsl:with-param name="key" select="concat($prefix,$string)"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="contains($string, $delimiter)">
				<xsl:if test="not(starts-with($string, $delimiter))">
					<xsl:call-template name="_tokenize-delimiters">
						<xsl:with-param name="string" select="substring-before($string, $delimiter)" />
						<xsl:with-param name="delimiters" select="substring($delimiters, 2)" />
						<xsl:with-param name="prefix" select="$prefix"/>
					</xsl:call-template>
				</xsl:if>
				<xsl:text> </xsl:text>
				<xsl:call-template name="_tokenize-delimiters">
					<xsl:with-param name="string" select="substring-after($string, $delimiter)" />
					<xsl:with-param name="delimiters" select="$delimiters" />
					<xsl:with-param name="prefix" select="$prefix"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="_tokenize-delimiters">
					<xsl:with-param name="string" select="$string" />
					<xsl:with-param name="delimiters" select="substring($delimiters, 2)" />
					<xsl:with-param name="prefix" select="$prefix"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
