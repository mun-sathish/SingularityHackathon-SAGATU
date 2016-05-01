<?php
/*
Plugin Name: Disable WooCommerce Reviews
Plugin URI: https://wordpress.org/plugins/disable-woocommerce-reviews/
Description: Disable WooCommerce reviews on all products.
Author: pipdig
Author URI: http://www.pipdig.co/
Version: 1.0
License: GPLv2 or later
License URI: http://www.gnu.org/licenses/gpl-2.0.html
*/
/*
    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*/

if (!defined('ABSPATH')) {
	die;
}

function disable_woocommerce_reviews_plugin($tabs) {
	unset($tabs['reviews']);
	return $tabs;
}
add_filter( 'woocommerce_product_tabs', 'disable_woocommerce_reviews_plugin', 99 );
	
function disable_woocommerce_reviews_product_notice() { ?>
	<style scoped>.comment_status_field{opacity:.4;pointer-events:none;}</style>
	<p style="font-style:italic;color:red;margin-left:5px"><?php _e('Product reviews are currently disabled.', 'disable-woocommerce-reviews'); ?></p><?php
}
add_action( 'woocommerce_product_options_reviews' , 'disable_woocommerce_reviews_product_notice', 10, 0 );
	
function disable_woocommerce_remove_metaboxes() {
	remove_meta_box( 'commentsdiv' , 'product' , 'normal' );
}
add_action( 'add_meta_boxes' , 'disable_woocommerce_remove_metaboxes', 99 );

function disable_woocommerce_remove_dashboard_widgets() {
    remove_meta_box( 'woocommerce_dashboard_recent_reviews', 'dashboard', 'normal');
}
add_action('wp_dashboard_setup', 'disable_woocommerce_remove_dashboard_widgets', 40);