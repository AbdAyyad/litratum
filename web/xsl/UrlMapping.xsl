<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <xsl:text>&#xa;</xsl:text>
        <xsl:call-template name="map"/>
    </xsl:template>

    <xsl:template name="map">
        <xsl:for-each select="maps/map">
            <xsl:value-of select="@url"/>
            <xsl:text>&#xa;</xsl:text>
            <xsl:value-of select="@action-name"/>
            <xsl:text>&#xa;</xsl:text>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>