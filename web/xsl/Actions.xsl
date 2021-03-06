<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="actions">
        <xsl:text>&#xa;</xsl:text>
        <xsl:call-template name="attribute"/>
    </xsl:template>

    <xsl:template name="attribute">
        <xsl:for-each select="action">
            <xsl:value-of select="@name"/>
            <xsl:text>&#xa;</xsl:text>
            <xsl:value-of select="@class"/>
            <xsl:text>&#xa;</xsl:text>
            <xsl:value-of select="@jsp"/>
            <xsl:text>&#xa;</xsl:text>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>