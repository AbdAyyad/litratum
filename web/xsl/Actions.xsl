<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="actions">
        <xsl:call-template name="attribute"/>
    </xsl:template>

    <xsl:template name="attribute">
        <xsl:text>&#xa;</xsl:text>
        <xsl:value-of select="action/@name"/>
        <xsl:text>&#xa;</xsl:text>
        <xsl:value-of select="action/@class"/>
        <xsl:text>&#xa;</xsl:text>
        <xsl:value-of select="action/@jsp"/>
    </xsl:template>
</xsl:stylesheet>