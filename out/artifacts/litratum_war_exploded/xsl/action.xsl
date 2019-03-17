<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <!--<xsl:output indent="yes"/>-->

    <xsl:template match="/">
        <xsl:apply-templates mode="print" select="actions/action"/>
    </xsl:template>

    <xsl:template match="action" mode="print">
        <xsl:text>&#xa;</xsl:text>
        <xsl:value-of select="@url"/>
        <xsl:text>&#xa;</xsl:text>
        <xsl:value-of select="@class"/>
    </xsl:template>

</xsl:stylesheet>